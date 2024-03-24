package com.comrades.article

//class MainMenuActivity_old : AppCompatActivity() {
//    private lateinit var adapter: ArticleAdapter_old
//    private lateinit var viewPager: ViewPager2
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_menu)
//
//        if (savedInstanceState == null) {
//            supportFragmentManager.commit {
//                setReorderingAllowed(true)
//                add<NavbarFragment>(R.id.navbar, "navbar")
//            }
//        }
//
//        val progress = findViewById<FrameLayout>(R.id.progress_bar)
//
//
//        val myCallback =  { result: List<ArticleResponse> ->
//            progress.isVisible = false
//            adapter = ArticleAdapter_old(this, result)
//            viewPager = findViewById(R.id.pager)
//            viewPager.adapter = adapter
//        }
//
//        Controller.loadData(myCallback)
//    }
//
//}