package Holders;

import android.view.View;
import android.widget.TextView;

import com.example.esc.moviestwopane.R;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed on 9/13/2016.
 */
public class ReviewHolder {

    public TextView name;
    public TextView content;

    public ReviewHolder(View view) {
        this.name = (TextView)view.findViewById(R.id.reviewer);
        this.content = (TextView)view.findViewById(R.id.reviewe);
    }

    public TextView getName() {
        return name;
    }

    public void setName(TextView name) {
        this.name = name;
    }

    public TextView getContent() {
        return content;
    }

    public void setContent(TextView content) {
        this.content = content;
    }
}
