package dedi.tsm.testdevelopment.api.respone
import android.content.ClipData.Item


class ItemListResponse {

    var message: String? = null
    lateinit var data: List<Item>
}