package demo.architecture.application;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;

import java.util.Map;

/**
 * @author suman dhungana
 */
public class ResponseUtil {
    private ResponseUtil(){}
    private static final String CONTENT_TYPE_HEADER = "Content-Type";
    private static final String APPLICATION_JSON = "application/json";


    public static void ok(RoutingContext context, JsonObject result) {
        context.response()
                .setStatusCode(200)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(result.encodePrettily());
    }


    public static void ok(RoutingContext context, JsonObject result, Map<String, String> headers) {
        HttpServerResponse response = context.response();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            response.putHeader(entry.getKey(), entry.getValue());
        }
        response.setStatusCode(200)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(result.toBuffer());
    }

    public static void ok(RoutingContext context, String result, Map<String, String> headers) {
        HttpServerResponse response = context.response();
        for (Map.Entry<String, String> entry : headers.entrySet()) {
            response.putHeader(entry.getKey(), entry.getValue());
        }
        response.setStatusCode(200)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(result);
    }

    public static void ok(RoutingContext context, String  result) {
        context.response()
                .setStatusCode(200)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(result);
    }

    public static void created(RoutingContext context) {
        context.response()
                .setStatusCode(201)
                .end();
    }

    public static void badRequest(RoutingContext context, Throwable ex) {
        context.response()
                .setStatusCode(400)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(new JsonObject()
                        .put("error", ex.getMessage())
                        .encodePrettily());
    }

    public static void badRequest(RoutingContext context, JsonObject cause) {
        context.response()
                .setStatusCode(400)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(cause.toBuffer());
    }

    public static void unauthorized(RoutingContext context, JsonObject cause) {
        context.response()
                .setStatusCode(401)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(cause.toBuffer());
    }

    public static void notFound(RoutingContext context) {
        context.response()
                .setStatusCode(404)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(new JsonObject()
                        .put("error", "Resources not found")
                        .encodePrettily());
    }

    public static void internalError(RoutingContext context, JsonObject cause) {
        context.response()
                .setStatusCode(500)
                .putHeader(CONTENT_TYPE_HEADER, APPLICATION_JSON)
                .end(cause.toBuffer());
    }
}
