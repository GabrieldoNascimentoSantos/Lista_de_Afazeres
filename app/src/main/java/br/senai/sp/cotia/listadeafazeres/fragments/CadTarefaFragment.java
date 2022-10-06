package br.senai.sp.cotia.listadeafazeres.fragments;

import android.app.DatePickerDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

import br.senai.sp.cotia.listadeafazeres.R;
import br.senai.sp.cotia.listadeafazeres.database.AppDatabase;
import br.senai.sp.cotia.listadeafazeres.databinding.FragmentCadTarefaBinding;
import br.senai.sp.cotia.listadeafazeres.model.Tarefa;

public class CadTarefaFragment extends Fragment {
    private FragmentCadTarefaBinding binding;
    // variavel para o DatePicker
    DatePickerDialog datePicker;
    // variáveis para o dia, mes e ano
    int year, month, day;
    // variável par aa data atual
    Calendar dataAtual;
    // variável para a data formatada
    String dataEscolhida = "";
    //variável para acessar a data base
    AppDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia a database
        database = AppDatabase.getDatabase(getActivity());

        // instancia o binding
        binding = FragmentCadTarefaBinding.inflate(inflater, container, false);

        // instancia a data atual
        dataAtual = Calendar.getInstance();

        // descobre o dia, mês e ano atual
        year = dataAtual.get(Calendar.YEAR);
        month = dataAtual.get(Calendar.MONTH);
        day = dataAtual.get(Calendar.DAY_OF_MONTH);


        // instanciando o DatePicker
        datePicker = new DatePickerDialog(getContext(), (view, ano, mes, dia)->{
            // cai aqui toda vez que clica no OK do DatePickerDialog
            // passa para as variáveis globais
            year = ano;
            month = mes;
            day = dia;
            // formata a String da dataEscolhida
            dataEscolhida = String.format("%02d/%02d/%04d", day, month+1, year);
            // Jogar a String no botão
            binding.botaoData.setText(dataEscolhida);
        }, year, month, day);

        //listener do botão de data
        binding.botaoData.setOnClickListener(view -> {
            // abre o datepicke
            datePicker.show();
        });

        //listener do botao salvar
        binding.botaoSalvar.setOnClickListener(v ->{
            //validar os campos
            if (binding.editTitulo.getText().toString().isEmpty()){
                binding.editTitulo.setError("Informe um Titulo");
                Toast.makeText(getActivity().getBaseContext(), R.string.validacao, Toast.LENGTH_SHORT).show();
                binding.editTitulo.requestFocus();
            }else if (dataEscolhida.isEmpty()){
                binding.editTitulo.setError("Informe uma Data");
                Toast.makeText(getActivity().getBaseContext(), R.string.validData, Toast.LENGTH_SHORT).show();
            }else {
                //Criar um objeto Tarefa
                Tarefa tarefa = new Tarefa();
                // popula a tarefa
                tarefa.setTitulo((binding.editTitulo.getText().toString()));
                tarefa.setDescricao(binding.editDescricao.getText().toString());
                // criar um Calendar e popular com a data que foi selecionada
                Calendar dataRealizacao = Calendar.getInstance();
                dataRealizacao.set(year, month, day);
                //passar para a tarefa os milissegundos da data
                tarefa.setDataPrevista(dataRealizacao.getTimeInMillis());
                // criar um Calendar para a data atual
                Calendar dataAtual = Calendar.getInstance();
                tarefa.setDataCriacao(dataAtual.getTimeInMillis());
                //salvar a tarefa no Banco de Dados
                new InsertTarefa().execute(tarefa);
            }
        });

        // retorna a view raiz do binding
        return binding.getRoot();
    }
    //classe para a Task de Inserir Tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {
        @Override
        protected void onPreExecute() {
            Log.w("PASSOU", "no OnPreExecute");
            super.onPreExecute();
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            Log.w("PASSOU", "no OnProgressUpdate");
            super.onProgressUpdate(values);
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            Log.w("PASSOU", "no DoInBackground");
            // extrair a Tarefa do vetor
            Tarefa t = tarefas[0];
            try {
                database.getTarefaDao().insert(t);
                return "ok";
            }catch (Exception e){
                e.printStackTrace();
                // retorna a mensagem de erro caso tenha dado erro
                return  e.getMessage();
            }
        }
        @Override
        protected void onPostExecute(String resultado) {
            if (resultado.equals("ok")){
                Log.w("RESULTADO", "IUUUUUUUUUUUUUUUUUUUUUUPIII");
                //aciona o botão de voltar
                getActivity().onBackPressed();
            }else {
                Log.w("RESULTADO", resultado);
                Toast.makeText(getContext(),"DEU RUIM"+resultado, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
