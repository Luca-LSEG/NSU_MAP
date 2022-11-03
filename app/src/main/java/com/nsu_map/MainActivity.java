package com.nsu_map;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.UiThread;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.naver.maps.geometry.LatLng;
import com.naver.maps.map.LocationTrackingMode;
import com.naver.maps.map.MapFragment;
import com.naver.maps.map.MapView;
import com.naver.maps.map.NaverMap;
import com.naver.maps.map.OnMapReadyCallback;
import com.naver.maps.map.UiSettings;
import com.naver.maps.map.overlay.Marker;
import com.naver.maps.map.util.FusedLocationSource;


// 네이버 map 객체 가져오기
public class MainActivity extends FragmentActivity
        implements OnMapReadyCallback {

    //내 위치 오버레이
    private static final int LOCATION_PERMiSION_REQUEST_CODE =1000;
    private FusedLocationSource locationSource;
    private NaverMap naverMap;
    private MapView mapView;

    // 채움 이동 버튼
    Button mbtn_url;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        locationSource =
                new FusedLocationSource(this, LOCATION_PERMiSION_REQUEST_CODE);

        //지도 객체 생성
        FragmentManager fm = getSupportFragmentManager();
        MapFragment mapFragment = (MapFragment) fm.findFragmentById(R.id.map_fragment);
        if (mapFragment == null) {
            mapFragment = MapFragment.newInstance();
            fm.beginTransaction().add(R.id.map_fragment, mapFragment).commit();
        }
        mapFragment.getMapAsync(this);

        //채움 메뉴 홈페이지 이동 이미지
        mbtn_url = findViewById((R.id.btn_url));

        mbtn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsu.ac.kr/?m1=page%25&menu_id=485%25"));
                startActivity(urlintent);
            }
        });

        //채움 메뉴 홈페이지 이동 버튼
        mbtn_url = findViewById((R.id.btn_url2));

        mbtn_url.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent urlintent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://nsu.ac.kr/?m1=page%25&menu_id=485%25"));
                startActivity(urlintent);
            }
        });
    }





    @UiThread
    @Override
    public void onMapReady(@NonNull NaverMap naverMap) {

        //내 위치 오버레이
        naverMap.setLocationSource(locationSource);
        naverMap.setLocationTrackingMode(LocationTrackingMode.NoFollow);

        //마커 테스트
        Marker marker = new Marker();
        marker.setPosition(new LatLng(36.91, 127.1435));
        marker.setMap(naverMap);



        // UI 설정(제스처)
        UiSettings uiSettings = naverMap.getUiSettings();
        uiSettings.setZoomGesturesEnabled(true);      // 줌 활성화
        uiSettings.setLocationButtonEnabled(true);
        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setCompassEnabled(true);       // 네이버 줌 버튼 활성화


    }

}


