package com.dipannita.dotanimation;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.cardview.widget.CardView;

public class CoolAnimator extends FrameLayout {
    Integer anmcolor;
    ImageView animateimg;;
    FrameLayout frameanimate;
    CardView cardlay;
    View dtoprogview;

    Context mcon;
//    @Override
//    public View getRootView() {
//        View dtoprogview = LayoutInflater.from(mcon).inflate(R.layout.layout_circle, null);
//
//        viewinit(context);
//        return dtoprogview ;
//    }




    public CoolAnimator(Context context) {
        super(context);
        mcon = context;
        init(context);

    }
    public CoolAnimator(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CoolAnimator, 0, 0);
        try {
            anmcolor = ta.getColor(R.styleable.CoolAnimator_anim_color, Color.GREEN);
        } finally {
            ta.recycle();
        }
        //
        init(context);
    }

    public CoolAnimator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {

        viewinit(context);
        //do stuff that was in your original constructor...
    }
    private void viewinit(Context context)
    {

       // setOrientation(LinearLayout.VERTICAL);
          FrameLayout finalframe = new FrameLayout(context);
         animateimg = new ImageView(context);
        animateimg.setImageResource(R.drawable.awaiting_status_outline);
        animateimg.setColorFilter(anmcolor, PorterDuff.Mode.SRC_IN);
       ImageView innerimg = new ImageView(context);
        innerimg.setImageResource(R.drawable.awaiting_status_innerpart);
         frameanimate = new FrameLayout(context);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        lp.gravity = Gravity.CENTER;
        lp.setMargins(12, 12, 12, 12);
        frameanimate.addView(animateimg);


        CardView cardview = new CardView(context);
        cardview.setRadius(45.0f);
        cardview.setForegroundGravity(Gravity.CENTER);

        cardview.addView(frameanimate);

        //finalframe.addView(innerimg);
        finalframe.addView(cardview);
        addView(finalframe);
        addView(innerimg,lp);
//        addView(animateimg, 2);
      // addView(cardview, 0);
        startanimation();

//        animateimg = dtoprogview.findViewById(R.id.animateview);
       // cardlay = dtoprogview.findViewById(R.id.cardviewlayout);
       // frameanimate = dtoprogview.findViewById(R.id.frameanimate);
       // startanimation();
    }

    private void startanimation()
    {


        final ScaleAnimation unscal_grow = new ScaleAnimation(0f, 4.0f, 0f, 4.0f, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        unscal_grow .setDuration(2000);
        final ScaleAnimation unscal_shrink = new ScaleAnimation(4.0f, 0f, 4.0f, 0f, Animation.RELATIVE_TO_SELF, (float) 0.5, Animation.RELATIVE_TO_SELF, (float) 0.5);
        unscal_grow .setDuration(3000);

        final AlphaAnimation animation1 = new AlphaAnimation(0.0f, 0.8f);
        animation1.setDuration(2000);
        //    animation1.setFillAfter(true);
        final AlphaAnimation animation2 = new AlphaAnimation(0.8f, 0.0f);
        animation1.setDuration(3000);
        //  animation1.setFillAfter(true);
        //   animateimg.startAnimation(unscal_shrink);
        animateimg.startAnimation(unscal_grow);
        unscal_grow.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

                frameanimate.startAnimation(animation1);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animateimg.setScaleX(4.0f);
                animateimg.setScaleY(4.0f);
                //  animateimg.setAlpha(0.9f);

                animateimg.startAnimation(unscal_shrink);


            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });

        unscal_shrink.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                //animateimg.setAlpha(0.9f);
                animateimg.setScaleX(4.0f);
                animateimg.setScaleY(4.0f);
                frameanimate.startAnimation(animation2);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

                animateimg.startAnimation(unscal_grow);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
