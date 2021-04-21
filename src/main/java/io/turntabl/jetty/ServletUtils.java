package io.turntabl.jetty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.zip.GZIPInputStream;

public class ServletUtils {
    private final Logger logger = LoggerFactory.getLogger(ServletUtils.class);

    public String decompress(HttpServletRequest req) {
        try (
                GZIPInputStream gzip = new GZIPInputStream(req.getInputStream());
                BufferedReader in = new BufferedReader(new InputStreamReader(gzip))
        ) {
            StringBuilder sb = new StringBuilder();
            String tmp;
            while ((tmp = in.readLine()) != null) {
                sb.append(tmp);
            }
            logger.info(sb.toString());
            return sb.toString();
        } catch (IOException iox) {
            logger.error("IOException: ", iox);
        }
        return "";
    }

}
