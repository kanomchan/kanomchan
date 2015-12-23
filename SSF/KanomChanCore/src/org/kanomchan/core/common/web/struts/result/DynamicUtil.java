package org.kanomchan.core.common.web.struts.result;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.zip.GZIPOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.json.SerializationParams;

import com.opensymphony.xwork2.util.TextParseUtil;

public class DynamicUtil {
	
	
    public static final String REGEXP_PATTERN = "regexp";
    public static final String WILDCARD_PATTERN = "wildcard";
    /* package */ static final String SPLIT_PATTERN = "split";
    /* package */ static final String JOIN_STRING = "join";
    /* package */ static final String ARRAY_BEGIN_STRING = "array-begin";
    /* package */ static final String ARRAY_END_STRING = "array-end";

	public static Set<String> commaDelimitedStringToSet(String commaDelim){
        if ((commaDelim == null) || (commaDelim.trim().length() == 0))
            return null;
        return TextParseUtil.commaDelimitedStringToSet(commaDelim);
	}

	public static List<Pattern> processIncludePatterns(Set<String> commaDelimitedStringToSet, String regexpPattern) {
		// TODO Auto-generated method stub
		return null;
	}

    public static boolean isGzipInRequest(HttpServletRequest request) {
        String header = request.getHeader("Accept-Encoding");
        return (header != null) && (header.indexOf("gzip") >= 0);
    }
    

    public static void writeJSONToResponse(SerializationParams serializationParams) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(serializationParams.getSerializedJSON()))
            stringBuilder.append(serializationParams.getSerializedJSON());

        if (StringUtils.isNotBlank(serializationParams.getWrapPrefix()))
            stringBuilder.insert(0, serializationParams.getWrapPrefix());
        else if (serializationParams.isWrapWithComments()) {
            stringBuilder.insert(0, "/* ");
            stringBuilder.append(" */");
        } else if (serializationParams.isPrefix())
            stringBuilder.insert(0, "{}&& ");

        if (StringUtils.isNotBlank(serializationParams.getWrapSuffix()))
            stringBuilder.append(serializationParams.getWrapSuffix());

        String json = stringBuilder.toString();

//        if (LOG.isDebugEnabled()) {
//            LOG.debug("[JSON]" + json);
//        }

        HttpServletResponse response = serializationParams.getResponse();

        // status or error code
        if (serializationParams.getStatusCode() > 0)
            response.setStatus(serializationParams.getStatusCode());
        else if (serializationParams.getErrorCode() > 0)
            response.sendError(serializationParams.getErrorCode());

        // content type
        response.setContentType(serializationParams.getContentType() + ";charset="
                + serializationParams.getEncoding());

        if (serializationParams.isNoCache()) {
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "No-cache");
        }

        if (serializationParams.isGzip()) {
            response.addHeader("Content-Encoding", "gzip");
            GZIPOutputStream out = null;
            InputStream in = null;
            try {
                out = new GZIPOutputStream(response.getOutputStream());
                in = new ByteArrayInputStream(json.getBytes(serializationParams.getEncoding()));
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            } finally {
                if (in != null)
                    in.close();
                if (out != null) {
                    out.finish();
                    out.close();
                }
            }

        } else {
            response.setContentLength(json.getBytes(serializationParams.getEncoding()).length);
            PrintWriter out = response.getWriter();
            out.print(json);
        }
    }
}
