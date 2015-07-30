package androidhive.info.materialdesign.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidhive.info.materialdesign.R;

/**
 * Created by a.stankiewicz on 2015-07-28.
 */
public class ImageAdapter extends PagerAdapter {
    Context context;
    private int[] Images = new int[] {R.drawable.startfirst, R.drawable.startsecond, R.drawable.startthird};

    public ImageAdapter(Context context){
        this.context = context;
    }

    @Override
    public int getCount() {
        return Images.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(context);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setImageResource(Images[position]);
        container.addView(imageView, 0);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((ImageView) object);
    }
}