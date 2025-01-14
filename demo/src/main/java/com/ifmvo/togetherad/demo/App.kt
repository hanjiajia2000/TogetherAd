package com.ifmvo.togetherad.demo

import android.app.Application
import com.ifmvo.togetherad.baidu.TogetherAdBaidu
import com.ifmvo.togetherad.core.TogetherAd
import com.ifmvo.togetherad.csj.TogetherAdCsj
import com.ifmvo.togetherad.gdt.TogetherAdGdt

/*
 * Created by Matthew Chen on 2020-04-16.
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        /**
         * 自定义穿山甲的初始化配置
         * 可自行选择自定义穿山甲的配置，不配置就会使用穿山甲的默认值
         */
//        //使用TextureView控件播放视频,默认为SurfaceView,当有SurfaceView冲突的场景，可以使用TextureView
//        TogetherAdCsj.useTextureView = false
//        //标题栏的主题色
//        TogetherAdCsj.titleBarTheme = TTAdConstant.TITLE_BAR_THEME_DARK
//        //是否允许sdk展示通知栏提示
//        TogetherAdCsj.allowShowNotify = true
//        //是否在锁屏场景支持展示广告落地页
//        TogetherAdCsj.allowShowPageWhenScreenLock = true
//        //测试阶段打开，可以通过日志排查问题，上线时去除该调用
//        TogetherAdCsj.debug = true
//        //允许直接下载的网络状态集合
//        TogetherAdCsj.directDownloadNetworkType = TTAdConstant.NETWORK_STATE_WIFI or TTAdConstant.NETWORK_STATE_3G
//        //是否支持多进程，true支持
//        TogetherAdCsj.supportMultiProcess = false
//        //自定义网络库，demo中给出了okhttp3版本的样例，其余请自行开发或者咨询工作人员。
//        TogetherAdCsj.httpStack = MyOkStack()
        //初始化穿山甲
        TogetherAdCsj.init(context = this, adProviderType = AdProviderType.CSJ.type, csjAdAppId = "5001121", appName = this.getString(R.string.app_name))
        //初始化广点通
        TogetherAdGdt.init(context = this, adProviderType = AdProviderType.GDT.type, gdtAdAppId = "1101152570")
        //初始化百青藤
        TogetherAdBaidu.init(context = this, adProviderType = AdProviderType.BAIDU.type, baiduAdAppId = "e866cfb0")

        /**
         * 配置所有广告位ID
         */
        TogetherAdCsj.idMapCsj = mapOf(
                TogetherAdAlias.AD_SPLASH to "801121648",
                TogetherAdAlias.AD_NATIVE_SIMPLE to "901121737",
                TogetherAdAlias.AD_NATIVE_RECYCLERVIEW to "901121737",
                TogetherAdAlias.AD_BANNER to "901121987",
                TogetherAdAlias.AD_INTER to "901121725",
                TogetherAdAlias.AD_REWARD to "901121365"
        )

        TogetherAdGdt.idMapGDT = mapOf(
                TogetherAdAlias.AD_SPLASH to "8863364436303842593",
                TogetherAdAlias.AD_NATIVE_SIMPLE to "6040749702835933",
                TogetherAdAlias.AD_NATIVE_RECYCLERVIEW to "6040749702835933",
                TogetherAdAlias.AD_BANNER to "4080052898050840",
                TogetherAdAlias.AD_INTER to "1050691202717808",
                TogetherAdAlias.AD_REWARD to "2090845242931421"
        )

        TogetherAdBaidu.idMapBaidu = mapOf(
                TogetherAdAlias.AD_SPLASH to "2058622",
                TogetherAdAlias.AD_NATIVE_SIMPLE to "2058628",
                TogetherAdAlias.AD_NATIVE_RECYCLERVIEW to "2058628",
                TogetherAdAlias.AD_BANNER to "2015351",
                TogetherAdAlias.AD_INTER to "2403633",
                TogetherAdAlias.AD_REWARD to "5925490"
        )

        /**
         * 配置全局的广告商权重。
         * 如果在调用具体广告API时没有配置单次请求的权重，就会默认使用这个全局的权重
         * 如果不配置，TogetherAd会默认所有初始化的广告商权重相同
         *
         * 也可以在请求广告前设置，实时生效
         */
        TogetherAd.setPublicProviderRadio(mapOf(
                AdProviderType.GDT.type to 1,
                AdProviderType.BAIDU.type to 0,
                AdProviderType.CSJ.type to 0
        ))

        /**
         * 自定义图片加载方式
         * 用于自渲染类型的广告图片加载
         * 如果不配置，TogetherAd 会使用默认的图片加载方式
         * 主要考虑到：开发者可以自定义实现图片加载：渐变、占位图、错误图等
         */
//        TogetherAd.setCustomImageLoader(object : AdImageLoader {
//            override fun loadImage(context: Context, imageView: ImageView, imgUrl: String) {
//                Glide.with(context).load(imgUrl).into(imageView)
//            }
//        })
    }
}