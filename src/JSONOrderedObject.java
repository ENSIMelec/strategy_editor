import java.io.IOException;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.JSONStreamAware;

public class JSONOrderedObject extends LinkedHashMap<String, Object> implements Map<String, Object>, JSONAware, JSONStreamAware {

    @Override
	public String toJSONString() {
        return JSONObject.toJSONString(this);}

	@Override
	public void writeJSONString(Writer arg0) throws IOException {
		
	}

    /*@Override
	public void writeJSONString(Writer writer) throws IOException {
        JSONObject.writeJSONString(this, writer);}*/
}