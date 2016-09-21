package Holders;

import android.view.View;
import android.widget.TextView;

import com.example.esc.moviestwopane.R;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 9/13/2016.
 */
public class TrailerHolder {

    public TextView name;

    public TrailerHolder(View view) {
        this.name = (TextView)view.findViewById(R.id.trailerName);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

}
