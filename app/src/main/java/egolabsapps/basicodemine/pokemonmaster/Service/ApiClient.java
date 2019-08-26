package egolabsapps.basicodemine.pokemonmaster.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static String SERVICE_BASEURL = "https://pokeapi.co/api/v2/";
    private static Retrofit serviceRetrofit = null;
    private static int TIMEOUT = 30;


    public static Retrofit getPokemonApi() {

        if (serviceRetrofit == null) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            Gson gson = new GsonBuilder().setLenient().create();

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .addInterceptor(interceptor)
                    .build();

            serviceRetrofit = new Retrofit.Builder()
                    .baseUrl(SERVICE_BASEURL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return serviceRetrofit;

    }

}
