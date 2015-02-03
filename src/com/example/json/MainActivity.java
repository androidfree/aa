package com.example.json;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.json.bean.Animal;
import com.example.json.bean.CalendarInfo;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv;
	private String s;
	private List<Animal> list;
	private Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			if (msg.what == 0) {
				tv.setText((String) msg.obj.toString());
			}
		}

	};
	private CalendarInfo info;
	private String total;
	private boolean success;
	private StringBuffer sb;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		tv = (TextView) findViewById(R.id.tv);

		new Thread(new Runnable() {

			public void run() {
				s = getJson().toString();
				
				
				Message msg = new Message();
				msg.what = 0;
				msg.obj = getinfo3(s);
				
				handler.sendMessage(msg);
			}
		}).start();

	}
	//解析手机号归属地
	private String getinfo3(String s){
		try {
			String substring = s.substring(s.indexOf("{"), s.indexOf("}")+1);
			Log.i("info", substring);
			JSONObject jsonObject = new JSONObject(substring);
			String Mobile = jsonObject.getString("Mobile");
			String Province = jsonObject.getString("Province");
			String City = jsonObject.getString("City");
			String Corp = jsonObject.getString("Corp");
			String info = "您的查询结果是："+Mobile+"归属地："+Province+""+City+""+Corp;
			return info;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
		
		
	}
	// 解析带数组的json
 	private List<Animal> getinfo2(String s) {
		try {
			list = new ArrayList<Animal>();
			JSONObject jsonObject = new JSONObject(s);
			 total = jsonObject.getString("total");
			 success = jsonObject.getBoolean("success");
			 
			 JSONArray arrayData = jsonObject.getJSONArray("arrayData");
			 for (int i = 0; i < arrayData.length(); i++) {
				JSONObject item = arrayData.getJSONObject(i);
				Animal animal = new Animal();
				animal.setId(item.getInt("id"));
				animal.setName(item.getString("name"));
				list.add(animal);
			}
			 return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}

	// 解析不带数组的json
	private String getinfo1(String s) {
		Log.i("info", s);
		try {
			JSONObject jsonObject = new JSONObject(s);
			jsonObject = jsonObject.getJSONObject("userbean");
			

			String Uid;
			String Showname;
			String Avtar;
			String State;

			Uid = jsonObject.getString("Uid");
			Showname = jsonObject.getString("Showname");
			Avtar = jsonObject.getString("Avtar");
			State = jsonObject.getString("State");
			String date = "Uid=" + Uid + "----" + "State=" + Showname;
			return date;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	// 从网络获取数据
	private StringBuffer getJson() {
		try {
			HttpClient httpClient = new DefaultHttpClient();
			HttpUriRequest request = new HttpGet("http://10.0.2.2/bb.json");
			HttpResponse response = httpClient.execute(request);
			int code = response.getStatusLine().getStatusCode();

			if (code == 200) {

				HttpEntity entity = response.getEntity();
				InputStream is = entity.getContent();
				StringBuffer buffer2 = new StringBuffer();
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = is.read(buffer)) != -1) {
					String info = new String(buffer, 0, len, "utf-8");
					buffer2.append(info);

				}
				Log.i("info", buffer2.toString());
				return buffer2;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
		return null;
	}
}
