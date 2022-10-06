package br.senai.sp.cotia.listadeafazeres.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import br.senai.sp.cotia.listadeafazeres.R;
import br.senai.sp.cotia.listadeafazeres.adapter.TarefaAdapter;
import br.senai.sp.cotia.listadeafazeres.database.AppDatabase;
import br.senai.sp.cotia.listadeafazeres.databinding.FragmentCadSubTarefaBinding;
import br.senai.sp.cotia.listadeafazeres.model.Tarefa;

public class CadSubTarefaFragment extends Fragment {
    private FragmentCadSubTarefaBinding binding;
    private AppDatabase database;
    private TarefaAdapter adapter;
    private List<Tarefa> tarefas;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // instancia o binding
        binding = FragmentCadSubTarefaBinding.inflate(inflater, container, false);
        binding.editSubTarefa.setOnClickListener(v -> {
            NavHostFragment.findNavController(PrincipalFragment.this).navigate(R.id.action_fragmentPrincipal_to_fragment);
        });


        // retorna a view raiz do binding
        return binding.getRoot();
    }
}