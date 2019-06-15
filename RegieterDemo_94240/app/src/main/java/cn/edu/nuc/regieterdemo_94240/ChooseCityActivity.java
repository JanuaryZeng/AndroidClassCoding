package cn.edu.nuc.regieterdemo_94240;

import android.app.ExpandableListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ChooseCityActivity extends ExpandableListActivity {

    private String[] provinces  = {"辽宁","山西","河北","山东","台湾"};
    private String[][] cities = {
            {"沈阳", "大连", "鞍山", "抚顺", "本溪", "铁岭","阜新","锦州", "盘锦","辽阳","葫芦岛"},
            {"太原", "大同", "临汾", "晋中", "阳泉", "长治"},
            {"石家庄", "秦皇岛", "邯郸", "保定"},
            {"济南", "青岛", "临沂", "潍坊","烟台","莱芜"},
            {"台北", "高雄", "台中", "金门"}
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final ExpandableListAdapter adapter = new BaseExpandableListAdapter() {
            @Override
            public int getGroupCount() {
                return provinces.length;
            }

            @Override
            public int getChildrenCount(int groupPosition) {
                return cities[groupPosition].length;
            }

            @Override
            public Object getGroup(int groupPosition) {
                return provinces[groupPosition];
            }

            @Override
            public Object getChild(int groupPosition, int childPosition) {
                return cities[groupPosition][childPosition];
            }

            @Override
            public long getGroupId(int groupPosition) {
                return groupPosition;
            }

            @Override
            public long getChildId(int groupPosition, int childPosition) {
                return childPosition;
            }

            @Override
            public boolean hasStableIds() {
                return true;
            }

            @Override
            public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

                LinearLayout ll = new LinearLayout(ChooseCityActivity.this);
                ll.setOrientation(LinearLayout.VERTICAL);

                ImageView logo = new ImageView(ChooseCityActivity.this);
                ll.addView(logo);

                TextView textView = getTextView();
                textView.setText(getGroup(groupPosition).toString());
                ll.addView(textView);

                return ll;
            }

            private TextView getTextView(){

                AbsListView.LayoutParams lp = new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 64);

                TextView textView = new TextView(ChooseCityActivity.this);
                textView.setLayoutParams(lp);
                textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
                textView.setPadding(36, 0 , 0, 0);
                textView.setTextSize(20);

                return textView;
            }


            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

                TextView textView = getTextView();
                textView.setText(getChild(groupPosition,childPosition).toString());

                return textView;
            }

            @Override
            public boolean isChildSelectable(int groupPosition, int childPosition) {
                return true;
            }
        };

        setListAdapter(adapter);
        getExpandableListView().setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                Intent intent = getIntent();
                Bundle data = new Bundle();
                data.putString("city",adapter.getChild(groupPosition, childPosition).toString());
                intent.putExtras(data);

                ChooseCityActivity.this.setResult(0, intent);
                ChooseCityActivity.this.finish();

                return false;
            }
        });

    }
}
