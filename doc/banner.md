# Banner 横幅广告

Banner广告(横幅广告)位于app顶部、中部、底部任意一处，横向贯穿整个app页面；当用户与app互动时，Banner广告会停留在屏幕上，并可在一段时间后自动刷新

—— 引自广点通文档

```kotlin
class BannerActivity : AppCompatActivity() {

    companion object {
        fun action(context: Context) {
            context.startActivity(Intent(context, BannerActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_banner)

        AdHelperBanner.show(activity = this, alias = TogetherAdAlias.AD_BANNER, container = adContainer, listener = object : BannerListener {
            override fun onAdStartRequest(providerType: String) {
                //在开始请求之前会回调此方法，失败切换的情况会回调多次
                addLog("开始请求了，$providerType")
                /*
                 * 百度：设置'广告着陆页'动作栏的颜色主题，目前开放了七大主题：黑色、蓝色、咖啡色、绿色、藏青色、红色、白色(默认) 主题
                 */
//                if (providerType == AdProviderType.BAIDU.type) {
//                    AppActivity.setActionBarColorTheme(AppActivity.ActionBarColorTheme.ACTION_BAR_RED_THEME)
//                }
            }

            override fun onAdLoaded(providerType: String) {
                //广告请求成功的回调，每次请求只回调一次
                addLog("请求到了，$providerType")
            }

            override fun onAdFailed(providerType: String, failedMsg: String?) {
                //请求失败的回调，失败切换的情况会回调多次
                addLog("单个广告请求失败, $providerType, $failedMsg")
            }

            override fun onAdFailedAll() {
                //所有配置的广告商都请求失败了，只有在全部失败之后会回调一次
                addLog("全部请求失败了")
            }

            override fun onAdClicked(providerType: String) {
                //点击广告的回调
                addLog("点击了，$providerType")
            }

            override fun onAdExpose(providerType: String) {
                //广告展示曝光的回调；由于 Banner 广告存在自动刷新功能，所以曝光会回调多次，每次刷新都会回调
                addLog("曝光了，$providerType")
            }

            override fun onAdClose(providerType: String) {
                //广告被关闭的回调
                addLog("关闭了，$providerType")
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        //销毁，避免内存泄漏
        AdHelperBanner.destroy()
    }

    private var logStr = "日志: \n"

    private fun addLog(content: String?) {
        logStr = logStr + content + "\n"
        log.text = logStr

        info.post { info.fullScroll(View.FOCUS_DOWN) }
    }
}
```

可查看 Demo 中 [Banner横幅广告的示例代码](../demo/src/main/java/com/ifmvo/togetherad/demo/banner/BannerActivity.kt)