package Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.caceres.crudidat.R;
import model.Alumno;

import java.util.List;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.MyHolder> {

    private List<Alumno> listaAlumno;

    public AlumnoAdapter(List<Alumno> listaAlumno) {
        this.listaAlumno = listaAlumno;
    }

    public void SetAlumno(List<Alumno> alumnos) {
        this.listaAlumno = alumnos;
    }



    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno,parent,false);
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {

        Alumno alumno = listaAlumno.get(position);
        String name1 = alumno.getName();
        String lastname1= alumno.getLastname();
        holder.name.setText(name1);
        holder.lastname.setText(lastname1);
    }

    @Override
    public int getItemCount() {
        return listaAlumno.size();
    }


    public class MyHolder extends RecyclerView.ViewHolder{
        TextView name,lastname;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name);
            lastname = itemView.findViewById(R.id.item_lastname);


        }
    }
}
