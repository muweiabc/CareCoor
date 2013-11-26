package cis573.carecoor;

import java.util.ArrayList;
import java.util.List;

import cis573.carecoor.bean.Friend;
//import cis573.carecoor.gamesFragment.gamesAdapter.ViewHolder;
//import cis573.carecoor.bean.game;
import cis573.carecoor.bean.Game;
import android.R.integer;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class GameFragment extends Fragment{
	
	private ListView gameListView;
	private ArrayList<Game> games;
	private GameAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		adapter=new GameAdapter(getActivity());
		
		games=new ArrayList<Game>();
		games.add(new Game("ca","ddd"));
		games.add(new Game("zz","qqq"));	
		games.add(new Game("mm","tt"));
		adapter.setgameList(games);	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.game_fragment,container, false);
		gameListView=(ListView)view.findViewById(R.id.gamelist);
		gameListView.setAdapter(adapter);
		gameListView.setOnItemClickListener(listener);
		return view;
	}
	
	private OnItemClickListener listener=new OnItemClickListener(){
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Friend item = (Friend) arg0.getItemAtPosition(arg2);
			if(item != null) {
				final String url = item.getUrl();
				new AlertDialog.Builder(getActivity())
				.setTitle(R.string.dialog_title_friends)
				.setMessage(getString(R.string.dialog_friends_facebook_page, item.getName()))
				.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(Intent.ACTION_VIEW);
						intent.setData(Uri.parse(url));
						startActivity(intent);
					}
				}).setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						return;
					}
				}).show();
			}
		}
	};
	/*
	private ArrayAdapter<Game> adapter=new ArrayAdapter<Game>(getActivity(),R.layout.game_item,games){

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			// TODO Auto-generated method stub
			
			if(convertView==null){
				convertView=View.inflate(getActivity(), R.layout.game_item, null);
				TextView name=(TextView)convertView.findViewById(R.id.game_name);
				name.setText(games.get(position).getName());
			}
			return convertView;
		}
		
	};*/
}

class GameAdapter extends BaseAdapter {

	private Context mContext;
	private List<Game> gamelist;
	
	
	public GameAdapter(Context context) {
		this.mContext = context;
	}

	public void setgameList(List<Game> gameList) {
		this.gamelist = gameList;
	}

	@Override
	public int getCount() {
		return gamelist != null ? gamelist.size() : 0;
	}

	@Override
	public Object getItem(int arg0) {
		return gamelist != null ? gamelist.get(arg0) : null;
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder vh;
		if(convertView == null) {
			convertView = View.inflate(mContext, R.layout.game_item, null);
			vh = new ViewHolder();
			vh.name = (TextView) convertView;
			convertView.setTag(vh);
		} else {
			vh = (ViewHolder) convertView.getTag();
		}
		Game item = (Game) getItem(position);
		if(item != null) {
			vh.name.setText(item.getName());
		}
		return convertView;
	}
	
	private static class ViewHolder {
		TextView name;
	}
}




 