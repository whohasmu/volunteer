package volunteerapp.cyh.volunteer.Main;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class CutstomViewPager extends ViewPager{

        private boolean enabled;

        public CutstomViewPager(Context context) {
            super(context);
        }

        public CutstomViewPager(Context context, AttributeSet attrs) {
            super(context, attrs);
        }


        @Override
        public boolean onInterceptTouchEvent(MotionEvent ev) {
            if (enabled) {
                return super.onInterceptTouchEvent(ev);
            } else {
                if (MotionEventCompat.getActionMasked(ev) == MotionEvent.ACTION_MOVE) {
                // ignore move action
                } else {
                    if (super.onInterceptTouchEvent(ev)) {
                        super.onTouchEvent(ev);
                    }
                }
                return false;
            }
        }

        @Override
        public boolean onTouchEvent(MotionEvent ev) {
            if (enabled) {
                return super.onTouchEvent(ev);
            } else {
                return MotionEventCompat.getActionMasked(ev) != MotionEvent.ACTION_MOVE && super.onTouchEvent(ev);
            }
        }
        public void setPagingEnabled(boolean enabled) {
            this.enabled = enabled;
        }

}
