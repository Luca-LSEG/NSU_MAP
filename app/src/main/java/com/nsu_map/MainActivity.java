package com.nsu_map;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.CameraUpdate;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.overlay.Overlay;
import com.naver.maps.map.util.FusedLocationSource;


/**** 네이버 객체 api 가져오기 ****/
public class MainActivity extends FragmentActivity
        implements OnMapReadyCallback {

    /**** 내 위치 오버레이 ****/
    private static final int LOCATION_PERMiSION_REQUEST_CODE = 1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView;

    /**** 메인 버튼 이동 버튼 ****/
    Button mbtn_url;

    /****** 버스 시트 보여주기 *****/
    Button bussheet;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        locationSource =
                new FusedLocationSource(this, LOCATION_PERMiSION_REQUEST_CODE);


        /** 지도 객체 생성 **/
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);


        /** 메인 메뉴 홈페이지 이동 버튼 **/
        mbtn_url = findViewById((R.id.btn_url));
        mbtn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R5kK6FmuHak"));
                startActivity(urlintent);
            }
        });


        /** 메인 메뉴 홈페이지 이동 버튼 **/
        mbtn_url = findViewById((R.id.btn_url2));
        mbtn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=R5kK6FmuHak"));
                startActivity(urlintent);
            }
        });


        /****  테스트 시트 버튼 ****/
        bussheet = findViewById(R.id.bus);
        bussheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });


    }

    /**** 버스 시간표 시드 시간표 애니메이션 ****/
    private void showDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.bus_sheet);


        Button bus = dialog.findViewById(R.id.mbusbtn);
        Button bus2 = dialog.findViewById(R.id.bus_btn2);

        bus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsu.ac.kr/?m1=page%25&menu_id=59%25"));
                startActivity(urlintent);
            }
        });

        bus2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsu.ac.kr/?m1=page%25&menu_id=59%25"));
                startActivity(urlintent);
            }
        });

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity((Gravity.BOTTOM));
    }



    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        /**** 내 위치 오버레이 ****/
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);

        /****버스정류장 마커 ****/
        Marker marker = new Marker();
        marker.setPosition(new LatLng(36.9104, 127.1422));
        marker.setMap(naverMap);
        marker.setCaptionText("셔틀 버스 정류장");
        marker.setOnClickListener(new Overlay.OnClickListener() {
            @Override
            public boolean onClick(@NonNull Overlay overlay) {
                showDialog();
                return false;
            }
        });

        /**** 채움 마커 ****/
        Marker marker2 = new Marker();
        marker2.setCaptionText("채움");
        marker2.setPosition(new LatLng(36.909119, 127.147374));
        marker2.setMap(naverMap);


        /**** 네이버 지도 UI 설정 ****/
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setZoomGesturesEnabled(true);      // 줌 활성화
        uiSettings.setLocationButtonEnabled(true);
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setCompassEnabled(true);       // 네이버 줌 버튼 활성화


    }



}



