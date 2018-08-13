package br.edu.iff.pooa20181.wswithretrofit;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.List;

import br.edu.iff.pooa20181.wswithretrofit.interfaceRetrofit.iRetroFitGithub;
import br.edu.iff.pooa20181.wswithretrofit.model.Usuario;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(MainActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);



        Button btUsuario = (Button) findViewById(R.id.btUsuario);

        btUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRetroFitGithub githubUser = iRetroFitGithub.retrofit.create(iRetroFitGithub.class);
                final Call<Usuario> call = githubUser.getUsuario("longhibianca");

                dialog.show();

                call.enqueue(new Callback<Usuario>() {
                    @Override
                    public void onResponse(Call<Usuario> call, Response<Usuario> response) {

                        if(dialog.isShowing())
                            dialog.dismiss();

                        int code = response.code();
                        if (code==200){
                            Usuario usuario = response.body();
                            Toast.makeText(getBaseContext(),"Nome : "+usuario.getName(),Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Usuario> call, Throwable t) {

                        if(dialog.isShowing())
                            dialog.dismiss();

                        Toast.makeText(getBaseContext(),"Falha : ",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });

        Button btSeguidores = (Button) findViewById(R.id.btSeguidores);

        btSeguidores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iRetroFitGithub githubUser = iRetroFitGithub.retrofit.create(iRetroFitGithub.class);
                final Call<List<Usuario>> call = githubUser.getSeguidores("lglmoura");
                dialog.show();
                call.enqueue(new Callback<List<Usuario>>() {
                    @Override
                    public void onResponse(Call<List<Usuario>> call, Response<List<Usuario>> response) {
                        if(dialog.isShowing())
                            dialog.dismiss();
                        List<Usuario> lista = response.body();
                        for(Usuario usuario : lista){
                            Log.d("LLLUUUIIIZZZZ", usuario.getLogin());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Usuario>> call, Throwable t) {

                        if(dialog.isShowing())
                            dialog.dismiss();

                        Toast.makeText(getBaseContext(),"Flaha : ",Toast.LENGTH_LONG).show();

                    }
                });
            }
        });






    }
}
