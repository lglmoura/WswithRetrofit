package br.edu.iff.pooa20181.wswithretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import br.edu.iff.pooa20181.wswithretrofit.interfaceRetrofit.iRetroFitGithub;
import br.edu.iff.pooa20181.wswithretrofit.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btUsuario = (Button) findViewById(R.id.btUsuario);

        btUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRetroFitGithub githubUser = iRetroFitGithub.retrofit.create(iRetroFitGithub.class);
                final Call<Usuario> call = githubUser.getUsuario("longhibianca");

                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {
                        int code = response.code();
                        if (code==200){
                            Usuario usuario = response.body();
                            Toast.makeText(getBaseContext(),"Nome : "+usuario.getName(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

                        Toast.makeText(getBaseContext(),"Falha : ",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });






    }
}
