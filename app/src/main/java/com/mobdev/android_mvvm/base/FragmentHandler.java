package com.mobdev.android_mvvm.base;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.mobdev.android_mvvm.R;

import java.util.Stack;

/**
 * Created by mobdev125 on 2/20/18.
 */

public class FragmentHandler {
    private final FragmentManager fragmentManager;
    private Stack<String> _mFragmentStack;
    private BaseFragment mCurrentFragment;

    FragmentHandler(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
        if (_mFragmentStack == null) {
            _mFragmentStack = new Stack<>();
        }
    }

    boolean onBack(boolean isBack) {
        if (_mFragmentStack == null) {
            return false;
        }

        if (_mFragmentStack.size() > 1) {
            if (isBack) {
                popFragment();
            }
            return true;
        }
        return false;
    }

    void removeAllFragments() {
        Fragment f;
        while (_mFragmentStack.size() > 0 && (f = fragmentManager.findFragmentByTag(_mFragmentStack.pop())) != null) {
            fragmentManager.beginTransaction().remove(f).commit();
            ((BaseFragment) f).clear();
        }
        _mFragmentStack = null;
        mCurrentFragment = null;
    }

    BaseFragment gotoFirstFragment() {
        if (_mFragmentStack.size() > 1) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment f;
            while (_mFragmentStack.size() > 1 && (f = fragmentManager.findFragmentByTag(_mFragmentStack.pop())) != null) {
                transaction.remove(f);
                ((BaseFragment) f).clear();
            }
            f = fragmentManager.findFragmentByTag(_mFragmentStack.peek());
            transaction.show(f);
            transaction.commit();

            mCurrentFragment = (BaseFragment) f;
        }

        return mCurrentFragment;
    }

    void replaceFragment(BaseFragment fragment, int contentId) {
        if (fragment == null) {
            return;
        }

        String tag = fragment.toTag();
        Log.i("Navigation Fragment Tag", tag);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            Fragment fragment1 = fragmentManager.findFragmentByTag(tag);
            if (fragment1 != null) {
                transaction.hide(mCurrentFragment);
                transaction.show(fragment1);
                fragment1.onStart();
            }
            else {
                transaction.add(contentId, fragment, tag);
                transaction.hide(mCurrentFragment);
                transaction.addToBackStack(null);
            }
            mCurrentFragment.onStop();
        } else {
            transaction.add(contentId, fragment, tag);
            transaction.addToBackStack(null);
        }
        transaction.commit();

        mCurrentFragment = fragment;
    }

    void pushFragment(BaseFragment fragment, int contentId) {
        String tag = fragment.toTag();

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (mCurrentFragment != null) {
            transaction.setCustomAnimations(R.anim.slide_left_in, R.anim.slide_left_out);
            Fragment currentFragment = fragmentManager.findFragmentByTag(_mFragmentStack.peek());
            transaction.hide(currentFragment);
        }

        transaction.add(contentId, fragment, tag);
        transaction.addToBackStack(tag);
        _mFragmentStack.add(tag);

        transaction.commit();

        mCurrentFragment = fragment;
    }

    private void popFragment() {
        if (_mFragmentStack.size() > 1) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            Fragment currentFragment = fragmentManager.findFragmentByTag(_mFragmentStack.pop());
            Fragment fragment = fragmentManager.findFragmentByTag(_mFragmentStack.peek());
            transaction.setCustomAnimations(R.anim.slide_right_in, R.anim.slide_right_out);
            transaction.remove(currentFragment);
            transaction.show(fragment);
            transaction.commit();

            mCurrentFragment.clear();
            mCurrentFragment = (BaseFragment) fragment;

        }
    }

    BaseFragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
