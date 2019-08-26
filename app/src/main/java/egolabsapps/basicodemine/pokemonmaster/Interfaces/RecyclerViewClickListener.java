package egolabsapps.basicodemine.pokemonmaster.Interfaces;

import android.view.View;
import android.widget.TextView;

import egolabsapps.basicodemine.pokemonmaster.UI.CustomViews.RingView;

public interface RecyclerViewClickListener {
    void onItemClick(View v, RingView pokeDot, int position);
}
