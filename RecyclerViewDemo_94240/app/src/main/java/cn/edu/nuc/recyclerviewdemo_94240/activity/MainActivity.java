package cn.edu.nuc.recyclerviewdemo_94240.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.edu.nuc.recyclerviewdemo_94240.Fruit;
import cn.edu.nuc.recyclerviewdemo_94240.R;
import cn.edu.nuc.recyclerviewdemo_94240.adapter.FruitAdapter;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<Fruit>();

    private RecyclerView recycler_view = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_view = (RecyclerView) findViewById(R.id.recycle_view);

        initFruits();//初始化

//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
//
//        //闪电nearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//
//        recycler_view.setLayoutManager(linearLayoutManager);
//        //设置RecyclerView 线性布局

        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(4,
                StaggeredGridLayoutManager.VERTICAL);

        recycler_view.setLayoutManager(staggeredGridLayoutManager);

        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);

        recycler_view.setAdapter(fruitAdapter);
    }

    private void initFruits(){
        for (int i = 0; i < 2; i++){
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            fruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            fruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic);
            fruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            fruitList.add(watermelon);
            Fruit pear = new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic);
            fruitList.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            fruitList.add(grape);
            Fruit pineapple = new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            fruitList.add(pineapple);
            Fruit strawberry = new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            fruitList.add(strawberry);
            Fruit cherry = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            fruitList.add(cherry);
            Fruit mango = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            fruitList.add(mango);
        }
    }

    private String getRandomLengthName(String name){
        Random random = new Random();

        int length = random.nextInt(20) + 1;

        StringBuilder builder = new StringBuilder();

        for(int i = 0; i < length; i++){
            builder.append(name);
        }

        return builder.toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:
                Toast.makeText(this, "你点击了注销菜单项", Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                Toast.makeText(this, "你点击了退出菜单项", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
