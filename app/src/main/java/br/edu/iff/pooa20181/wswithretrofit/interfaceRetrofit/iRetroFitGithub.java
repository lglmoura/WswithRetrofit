package br.edu.iff.pooa20181.wswithretrofit.interfaceRetrofit;

import java.util.List;

import br.edu.iff.pooa20181.wswithretrofit.model.Usuario;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface iRetroFitGithub {

    @GET("/users/{usuario}")
    Call<Usuario> getUsuario(@Path("usuario") String usuario);

    @GET("/users/{usuario}/followers")
    Call<List<Usuario>> getSeguidores(@Path("usuario") String usuario);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}