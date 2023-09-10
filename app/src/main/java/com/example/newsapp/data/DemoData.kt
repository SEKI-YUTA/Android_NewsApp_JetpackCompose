package com.example.newsapp.data

import com.example.newsapp.ui.model.Article
import com.example.newsapp.ui.model.NewsResponse
import com.example.newsapp.ui.model.Source

// APIに呼び出し回数の制限があるので、頻繁に再起動等をする際にこのデータを使用する
class DemoData() {
    companion object {
        val demoResponse = NewsResponse(
            succeed = true,
            articles = listOf(
                Article(
                    "報知新聞社",
                    null,
                    "フィギュアスケート男子で２０１４年ソチ、１８年平昌五輪を連覇し、昨年７月にプロ転向した羽生結弦さんが４日、自身の公式ＳＮＳで結婚を発表した。以下は報告全文。",
                    "2023-08-04T14:15:00Z",
                    Source(null, "Hochi.news"),
                    "羽生結弦さんが結婚 自身の公式ＳＮＳで発表 - スポーツ報知",
                    "https://hochi.news/articles/20230804-OHT1T51335.html",
                    "https://hochi.news/images/2023/08/04/20230804-OHT1I51498-L.jpg"
                ),
                Article(
                    "「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO!",
                    null,
                    "",
                    "2023-08-04T13:19:44Z",
                    Source(null, "Nishinippon.co.jp"),
                    "ソフトバンクが延長11回、北の大地でサヨナラ負け3連敗 貯金はついに1、柳町達の逆転二塁打のリード守り切れず：「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO! - 西日本新聞me",
                    "https://nishispo.nishinippon.co.jp/article/792521",
                    "https://nishispo-static.nishinippon.co.jp/image/article/size1/4/a/6/c/4a6c07c8f74515e6f9a6ea46d4ab4bc7_1.jpg?20230805001329"
                ),
                Article(
                    null,
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg",
                    "",
                    "2023-08-04T12:47:47Z",
                    Source(null, "Little-beans.net"),
                    "楽天でRyzen 7 5800HノートPCが実質6万4800円（ただし8GBメモリー） - こまめブログ",
                    "https://little-beans.net/bargain/rakuten20230804-vivobook16x/",
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg"
                ),
                Article(
                    "報知新聞社",
                    null,
                    "フィギュアスケート男子で２０１４年ソチ、１８年平昌五輪を連覇し、昨年７月にプロ転向した羽生結弦さんが４日、自身の公式ＳＮＳで結婚を発表した。以下は報告全文。",
                    "2023-08-04T14:15:00Z",
                    Source(null, "Hochi.news"),
                    "羽生結弦さんが結婚 自身の公式ＳＮＳで発表 - スポーツ報知",
                    "https://hochi.news/articles/20230804-OHT1T51335.html",
                    "https://hochi.news/images/2023/08/04/20230804-OHT1I51498-L.jpg"
                ),
                Article(
                    "「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO!",
                    null,
                    "",
                    "2023-08-04T13:19:44Z",
                    Source(null, "Nishinippon.co.jp"),
                    "ソフトバンクが延長11回、北の大地でサヨナラ負け3連敗 貯金はついに1、柳町達の逆転二塁打のリード守り切れず：「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO! - 西日本新聞me",
                    "https://nishispo.nishinippon.co.jp/article/792521",
                    "https://nishispo-static.nishinippon.co.jp/image/article/size1/4/a/6/c/4a6c07c8f74515e6f9a6ea46d4ab4bc7_1.jpg?20230805001329"
                ),
                Article(
                    null,
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg",
                    "",
                    "2023-08-04T12:47:47Z",
                    Source(null, "Little-beans.net"),
                    "楽天でRyzen 7 5800HノートPCが実質6万4800円（ただし8GBメモリー） - こまめブログ",
                    "https://little-beans.net/bargain/rakuten20230804-vivobook16x/",
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg"
                ),
                Article(
                    "報知新聞社",
                    null,
                    "フィギュアスケート男子で２０１４年ソチ、１８年平昌五輪を連覇し、昨年７月にプロ転向した羽生結弦さんが４日、自身の公式ＳＮＳで結婚を発表した。以下は報告全文。",
                    "2023-08-04T14:15:00Z",
                    Source(null, "Hochi.news"),
                    "羽生結弦さんが結婚 自身の公式ＳＮＳで発表 - スポーツ報知",
                    "https://hochi.news/articles/20230804-OHT1T51335.html",
                    "https://hochi.news/images/2023/08/04/20230804-OHT1I51498-L.jpg"
                ),
                Article(
                    "「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO!",
                    null,
                    "",
                    "2023-08-04T13:19:44Z",
                    Source(null, "Nishinippon.co.jp"),
                    "ソフトバンクが延長11回、北の大地でサヨナラ負け3連敗 貯金はついに1、柳町達の逆転二塁打のリード守り切れず：「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO! - 西日本新聞me",
                    "https://nishispo.nishinippon.co.jp/article/792521",
                    "https://nishispo-static.nishinippon.co.jp/image/article/size1/4/a/6/c/4a6c07c8f74515e6f9a6ea46d4ab4bc7_1.jpg?20230805001329"
                ),
                Article(
                    null,
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg",
                    "",
                    "2023-08-04T12:47:47Z",
                    Source(null, "Little-beans.net"),
                    "楽天でRyzen 7 5800HノートPCが実質6万4800円（ただし8GBメモリー） - こまめブログ",
                    "https://little-beans.net/bargain/rakuten20230804-vivobook16x/",
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg"
                ),
                Article(
                    "報知新聞社",
                    null,
                    "フィギュアスケート男子で２０１４年ソチ、１８年平昌五輪を連覇し、昨年７月にプロ転向した羽生結弦さんが４日、自身の公式ＳＮＳで結婚を発表した。以下は報告全文。",
                    "2023-08-04T14:15:00Z",
                    Source(null, "Hochi.news"),
                    "羽生結弦さんが結婚 自身の公式ＳＮＳで発表 - スポーツ報知",
                    "https://hochi.news/articles/20230804-OHT1T51335.html",
                    "https://hochi.news/images/2023/08/04/20230804-OHT1I51498-L.jpg"
                ),
                Article(
                    "「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO!",
                    null,
                    "",
                    "2023-08-04T13:19:44Z",
                    Source(null, "Nishinippon.co.jp"),
                    "ソフトバンクが延長11回、北の大地でサヨナラ負け3連敗 貯金はついに1、柳町達の逆転二塁打のリード守り切れず：「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO! - 西日本新聞me",
                    "https://nishispo.nishinippon.co.jp/article/792521",
                    "https://nishispo-static.nishinippon.co.jp/image/article/size1/4/a/6/c/4a6c07c8f74515e6f9a6ea46d4ab4bc7_1.jpg?20230805001329"
                ),
                Article(
                    null,
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg",
                    "",
                    "2023-08-04T12:47:47Z",
                    Source(null, "Little-beans.net"),
                    "楽天でRyzen 7 5800HノートPCが実質6万4800円（ただし8GBメモリー） - こまめブログ",
                    "https://little-beans.net/bargain/rakuten20230804-vivobook16x/",
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg"
                ),
                Article(
                    "報知新聞社",
                    null,
                    "フィギュアスケート男子で２０１４年ソチ、１８年平昌五輪を連覇し、昨年７月にプロ転向した羽生結弦さんが４日、自身の公式ＳＮＳで結婚を発表した。以下は報告全文。",
                    "2023-08-04T14:15:00Z",
                    Source(null, "Hochi.news"),
                    "羽生結弦さんが結婚 自身の公式ＳＮＳで発表 - スポーツ報知",
                    "https://hochi.news/articles/20230804-OHT1T51335.html",
                    "https://hochi.news/images/2023/08/04/20230804-OHT1I51498-L.jpg"
                ),
                Article(
                    "「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO!",
                    null,
                    "",
                    "2023-08-04T13:19:44Z",
                    Source(null, "Nishinippon.co.jp"),
                    "ソフトバンクが延長11回、北の大地でサヨナラ負け3連敗 貯金はついに1、柳町達の逆転二塁打のリード守り切れず：「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO! - 西日本新聞me",
                    "https://nishispo.nishinippon.co.jp/article/792521",
                    "https://nishispo-static.nishinippon.co.jp/image/article/size1/4/a/6/c/4a6c07c8f74515e6f9a6ea46d4ab4bc7_1.jpg?20230805001329"
                ),
                Article(
                    null,
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg",
                    "",
                    "2023-08-04T12:47:47Z",
                    Source(null, "Little-beans.net"),
                    "楽天でRyzen 7 5800HノートPCが実質6万4800円（ただし8GBメモリー） - こまめブログ",
                    "https://little-beans.net/bargain/rakuten20230804-vivobook16x/",
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg"
                ),
                Article(
                    "報知新聞社",
                    null,
                    "フィギュアスケート男子で２０１４年ソチ、１８年平昌五輪を連覇し、昨年７月にプロ転向した羽生結弦さんが４日、自身の公式ＳＮＳで結婚を発表した。以下は報告全文。",
                    "2023-08-04T14:15:00Z",
                    Source(null, "Hochi.news"),
                    "羽生結弦さんが結婚 自身の公式ＳＮＳで発表 - スポーツ報知",
                    "https://hochi.news/articles/20230804-OHT1T51335.html",
                    "https://hochi.news/images/2023/08/04/20230804-OHT1I51498-L.jpg"
                ),
                Article(
                    "「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO!",
                    null,
                    "",
                    "2023-08-04T13:19:44Z",
                    Source(null, "Nishinippon.co.jp"),
                    "ソフトバンクが延長11回、北の大地でサヨナラ負け3連敗 貯金はついに1、柳町達の逆転二塁打のリード守り切れず：「おっ！」でつながる地元密着のスポーツ応援メディア 西スポWEB OTTO! - 西日本新聞me",
                    "https://nishispo.nishinippon.co.jp/article/792521",
                    "https://nishispo-static.nishinippon.co.jp/image/article/size1/4/a/6/c/4a6c07c8f74515e6f9a6ea46d4ab4bc7_1.jpg?20230805001329"
                ),
                Article(
                    null,
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg",
                    "",
                    "2023-08-04T12:47:47Z",
                    Source(null, "Little-beans.net"),
                    "楽天でRyzen 7 5800HノートPCが実質6万4800円（ただし8GBメモリー） - こまめブログ",
                    "https://little-beans.net/bargain/rakuten20230804-vivobook16x/",
                    "https://little-beans.net/wp-content/uploads/2023/08/Vivobook16X-M1603QA.jpg"
                ),
            ),
            status = "ok",
            totalResults = 0
        )
    }
}
