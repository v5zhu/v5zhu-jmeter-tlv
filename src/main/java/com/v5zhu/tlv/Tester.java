package com.v5zhu.tlv;

import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class Tester extends AbstractJavaSamplerClient {
	private String server;
	private int port;
	private String merId;
	private String termId;
	private String traceNo;
	private String phase;
	private SampleResult result;

	/** the cache of Terminal Connections */
	private static final ThreadLocal<Map<String, TLV>> tp = new ThreadLocal<Map<String, TLV>>() {
		@Override
		protected Map<String, TLV> initialValue() {
			return new HashMap<>();
		}
	};

	public void setupTest(JavaSamplerContext arg0) {
	}

	public Arguments getDefaultParameters() {
		Arguments params = new Arguments();
		params.addArgument("server", "192.168.13.120");
		params.addArgument("port", "37019");
		params.addArgument("merId", "000000000040003");
		params.addArgument("termId", "51400166");
		params.addArgument("phase", "signIn");
		params.addArgument("traceNo", "000002");
		return params;
	}

	public SampleResult runTest(JavaSamplerContext arg0) {
		server = arg0.getParameter("server");
		port = Integer.parseInt(arg0.getParameter("port"));
		merId = arg0.getParameter("merId");
		termId = arg0.getParameter("termId");
		traceNo = arg0.getParameter("traceNo");
		phase = arg0.getParameter("phase");

		result = new SampleResult();
		result.setSampleLabel(phase.toUpperCase());
		result.setThreadName(termId);
		result.setRequestHeaders(termId);

		try {
			result.sampleStart();
			Map<String, TLV> cp = tp.get();

			for (String x : phase.split(",")) {		
				if(x.equalsIgnoreCase("signIn")) {
                    String response = TLV.signIn(server, port, merId, termId, traceNo);
                    System.out.println("签到返回:"+response);
                    result.setResponseMessage(response);
                    if("00".equals(response)){
                        result.setSuccessful(true);
                    }else if("timeout".equals(response)){
                        result.setResponseCode("504");
                        result.setResponseData("timeout","utf-8");
                        result.setResponseHeaders("timeout");
                        result.setSuccessful(false);
                    }
                }
			}
			result.setEndTime(System.currentTimeMillis());
		} catch (Exception e) {
			result.setSuccessful(false);
			result.setResponseMessage("Exception: " + e);

			/* print stack trace */
			StringWriter stringWriter = new StringWriter();
			e.printStackTrace(new java.io.PrintWriter(stringWriter));
			result.setResponseData(stringWriter.toString(), "utf-8");
			result.setDataType(SampleResult.TEXT);
			result.setEndTime(System.currentTimeMillis());
		}
		return result;
	}
	public void teardownTest(JavaSamplerContext arg0) {
	}
}
