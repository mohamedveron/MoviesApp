package Holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.esc.movies.R;

/**
 * Created by Mohamed AbdEl Mohaimen Mohamed  on 8/2/2016.
 */
public class MovieHolder {
    public TextView title;
    public TextView id;
    public ImageView poster;

    public MovieHolder(View view){
        poster = (ImageView) view.findViewById(R.id.posterImage);
    }
    public ImageView getPoster() {
        return poster;
    }

    public void setPoster(ImageView poster) {
        this.poster = poster;
    }
}
