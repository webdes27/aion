package org.aion.api.server.rpc2;

import java.io.IOException;
import org.aion.api.envelope.JsonRpcRequest;
import org.aion.api.envelope.JsonRpcResponse;
import org.aion.api.schema.RequestDeserializer;
import org.aion.api.schema.ResponseSerializer;

public abstract class AbstractRpcProcessor {
    private RequestDeserializer reqDeserializer;
    private ResponseSerializer respSerializer;

    public AbstractRpcProcessor() {
        reqDeserializer = new RequestDeserializer(null /*not needed yet*/);
        respSerializer = new ResponseSerializer(null /*not needed yet*/);
    }

    public String process(String payload) throws IOException {
        JsonRpcRequest req = reqDeserializer.deserialize(payload);
        JsonRpcResponse resp = new JsonRpcResponse(execute(req), req.getId());
        return respSerializer.serialize(resp, req.getMethod());
    }

    protected abstract Object execute(JsonRpcRequest payload) throws IOException;
}