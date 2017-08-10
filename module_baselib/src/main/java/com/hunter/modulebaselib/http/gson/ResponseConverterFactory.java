package com.hunter.modulebaselib.http.gson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2017/8/10.
 */

public class ResponseConverterFactory extends Converter.Factory {


    private final Gson gson;

    private ResponseConverterFactory(Gson gson) {
        this.gson = gson;
    }

    public static ResponseConverterFactory create() {
        return create(new Gson());
    }

    @SuppressWarnings("ConstantConditions")
    public static ResponseConverterFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new ResponseConverterFactory(gson);
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations,
                                                            Retrofit retrofit) {
        return new GsonResponseBodyConverter<>(gson, type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
                                                          Annotation[] methodAnnotations, Retrofit retrofit) {
        return new GsonResponseBodyConverter<>(gson, type);
    }
}
