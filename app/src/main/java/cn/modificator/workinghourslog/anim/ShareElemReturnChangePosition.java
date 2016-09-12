package cn.modificator.workinghourslog.anim;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.support.v4.view.animation.FastOutSlowInInterpolator;
import android.transition.PathMotion;
import android.transition.Transition;
import android.transition.TransitionValues;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Modificator
 * time: 16/9/9.下午9:55
 * des:create file and achieve model
 */

public class ShareElemReturnChangePosition  extends Transition {
    private static final String TAG = "ShareElemReturnChangePosition";

    private static final String PROPNAME_POSITION = "custom_position:change_position:position";


    public ShareElemReturnChangePosition() {
        setPathMotion(new PathMotion() {
            @Override
            public Path getPath(float startX, float startY, float endX, float endY) {
                Path path = new Path();
                path.moveTo(startX, startY);

                float controlPointX = (startX + endX) / 3;
                float controlPointY = (startY + endY) / 2;

                path.quadTo(controlPointX, controlPointY, endX, endY);
                return path;
            }
        });
    }

    private void captureValues(TransitionValues values) {
        values.values.put(PROPNAME_POSITION, values.view.getBackground());

        Rect rect = new Rect();
        values.view.getGlobalVisibleRect(rect);
        values.values.put(PROPNAME_POSITION, rect);
    }


    @Override
    public void captureStartValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public void captureEndValues(TransitionValues transitionValues) {
        captureValues(transitionValues);
    }

    @Override
    public Animator createAnimator(ViewGroup sceneRoot,
                                   TransitionValues startValues, TransitionValues endValues) {
        if (null == startValues || null == endValues) {
            return null;
        }

        if (startValues.view.getId() > 0) {
            Rect startRect = (Rect) startValues.values.get(PROPNAME_POSITION);
            Rect endRect = (Rect) endValues.values.get(PROPNAME_POSITION);

            final View view = endValues.view;


            Path changePosPath = getPathMotion().getPath(startRect.centerX(), startRect.centerY(), endRect.centerX(), endRect.centerY() - endRect.height() / 2);

            ObjectAnimator objectAnimator = ObjectAnimator.ofObject(view, new PropPosition(PointF.class, "position", new PointF(startRect.centerX(), startRect.centerY())), null, changePosPath);
            objectAnimator.setInterpolator(new FastOutSlowInInterpolator());

            return objectAnimator;
        }
        return null;

    }

    static class PropPosition extends Property<View, PointF> {

        public PropPosition(Class<PointF> type, String name) {
            super(type, name);
        }

        public PropPosition(Class<PointF> type, String name, PointF startPos) {
            super(type, name);
            this.startPos = startPos;
        }

        PointF startPos;

        @Override
        public void set(View view, PointF topLeft) {

            int x = Math.round(topLeft.x);
            int y = Math.round(topLeft.y);

            int startX = Math.round(startPos.x);
            int startY = Math.round(startPos.y);

            int transY = y - startY;
            int transX = x - startX;

            view.setTranslationX(transX);
            view.setTranslationY(transY);
        }

        @Override
        public PointF get(View object) {
            return null;
        }
    }
}