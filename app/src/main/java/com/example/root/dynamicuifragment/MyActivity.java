package com.example.root.dynamicuifragment;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MyActivity extends FragmentActivity
        implements Headlines.OnHeadlineSelectedListener
    {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_article);
        if (findViewById(R.id.fragment_container) != null) {


            if (savedInstanceState != null) {
                return;
            }


            Headlines firstFragment = new Headlines();


            firstFragment.setArguments(getIntent().getExtras());


            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }

    }

        public void onArticleSelected(int position) {



            ArticleFragment articleFrag = (ArticleFragment)
                    getSupportFragmentManager().findFragmentById(R.id.article_fragment);

            if (articleFrag != null) {


                articleFrag.updateArticleView(position);

            } else {

                ArticleFragment newFragment = new ArticleFragment();
                Bundle args = new Bundle();
                args.putInt(ArticleFragment.ARG_POSITION, position);
                newFragment.setArguments(args);
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();


                transaction.replace(R.id.fragment_container, newFragment);
                transaction.addToBackStack(null);


                transaction.commit();
            }
        }


}
