package com.example.quand.recyclerviewwithswipe;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.nispok.snackbar.Snackbar;
import com.nispok.snackbar.SnackbarManager;
import com.nispok.snackbar.listeners.ActionClickListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SearchFragment extends Fragment implements RecyclerItemTouchHelper.RecyclerItemTouchHelperListener {

    private ArrayList<Person> persons;
    private RecyclerView recyclerView;
    private PersonAdapter adapter;
    private EditText edtSearch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.search_fragment, container, false);
        initView(view);
        initRecyclerView(view);
        return view;
    }

    private void initView(View view) {
        edtSearch = view.findViewById(R.id.edt_search);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.setPersons(adapter.filter(charSequence.toString()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void initRecyclerView(View view) {
        initData();
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager linear = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linear);
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), linear.getOrientation());
        recyclerView.addItemDecoration(divider);
        ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new RecyclerItemTouchHelper(0, ItemTouchHelper.LEFT, this);
        new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(recyclerView);
        adapter = new PersonAdapter(getContext(), persons);
        recyclerView.setAdapter(adapter);
    }

    public void initData() {
        persons = new ArrayList<>();
        persons.add(new Person("Nguyễn Hồng Quân", "Thanh Hóa"));
        persons.add(new Person("Nguyễn Hồng Sơn", "Hải Phòng"));
        persons.add(new Person("Nguyễn Ngọc Quang", "Hà Nội"));
        persons.add(new Person("Nguyễn Ngọc Thọ", "Nam Định"));
        persons.add(new Person("Nguyễn Hữu Tuấn", "Hà Nội"));
        persons.add(new Person("Nguyễn Thị Phượng", "Thanh Hóa"));
        Collections.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person person, Person t1) {
                if(person.getName().split(" ")[2].compareTo(t1.getName().split(" ")[2])>0) return 1;
                if(person.getName().split(" ")[2].compareTo(t1.getName().split(" ")[2])<0) return -1;
                return 0;
            }
        });
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int derection, int position) {
        if(viewHolder instanceof RecyclerView.ViewHolder){
            final Person itemDeleted = persons.get(position);
            final int index = position;
            adapter.removeItem(position);
            Snackbar snackbar = Snackbar.with(getContext())
                    .text("Đã xóa một mục!")
                    .duration(Snackbar.SnackbarDuration.LENGTH_SHORT)
                    .actionLabel("Hoàn tác")
                    .actionListener(new ActionClickListener() {
                        @Override
                        public void onActionClicked(Snackbar snackbar) {
                            adapter.addItem(itemDeleted, index);
                        }
                    });
            SnackbarManager.show(snackbar);
        }
    }
}
