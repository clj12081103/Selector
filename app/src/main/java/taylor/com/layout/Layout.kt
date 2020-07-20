package taylor.com.layout


import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.Typeface
import android.support.constraint.ConstraintHelper
import android.support.constraint.ConstraintLayout
import android.support.constraint.Guideline

import android.text.Editable
import android.util.TypedValue
import android.view.*
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.MarginLayoutParamsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import taylor.com.slectorkt.Selector


//<editor-fold desc="widget creation function">
inline fun ViewGroup.TextView(init: TextView.() -> Unit) =
    TextView(context).apply(init).also { addView(it) }

inline fun ViewGroup.ImageView(init: ImageView.() -> Unit) =
    ImageView(context).apply(init).also { addView(it) }

inline fun ViewGroup.Button(init: Button.() -> Unit) =
    Button(context).apply(init).also { addView(it) }

inline fun ViewGroup.View(init: View.() -> Unit): View =
    View(context).apply(init).also { addView(it) }

inline fun ViewGroup.RelativeLayout(init: RelativeLayout.() -> Unit) =
    RelativeLayout(context).apply(init).also { addView(it) }

inline fun ViewGroup.LinearLayout(init: LinearLayout.() -> Unit) =
    LinearLayout(context).apply(init).also { addView(it) }

inline fun ViewGroup.ConstraintLayout(init: ConstraintLayout.() -> Unit) =
    ConstraintLayout(context).apply(init).also { addView(it) }

inline fun ViewGroup.FrameLayout(init: FrameLayout.() -> Unit) =
    FrameLayout(context).apply(init).also { addView(it) }

inline fun ViewGroup.ViewFlipper(init: ViewFlipper.() -> Unit) =
    ViewFlipper(context).apply(init).also { addView(it) }

inline fun ViewGroup.EditText(init: EditText.() -> Unit) =
    EditText(context).apply(init).also { addView(it) }

inline fun ViewGroup.Selector(init: Selector.() -> Unit) =
    Selector(context).apply(init).also { addView(it) }

inline fun ConstraintLayout.Guideline(init: Guideline.() -> Unit) =
    Guideline(context).apply(init).also { addView(it) }

inline fun Context.ConstraintLayout(init: ConstraintLayout.() -> Unit): ConstraintLayout =
    ConstraintLayout(this).apply(init)

inline fun Context.LinearLayout(init: LinearLayout.() -> Unit): LinearLayout =
    LinearLayout(this).apply(init)

inline fun Context.FrameLayout(init: FrameLayout.() -> Unit) =
    FrameLayout(this).apply(init)


inline fun Context.TextView(init: TextView.() -> Unit) =
    TextView(this).apply(init)

inline fun Context.Button(init: Button.() -> Unit) =
    Button(this).apply(init)

inline fun Context.ImageView(init: ImageView.() -> Unit) =
    ImageView(this).apply(init)

inline fun Context.View(init: View.() -> Unit) =
    View(this).apply(init)

inline fun Context.EditText(init: EditText.() -> Unit) =
    EditText(this).apply(init)

inline fun Context.ViewFlipper(init: ViewFlipper.() -> Unit) =
    ViewFlipper(this).apply(init)

inline fun Context.Selector(init: Selector.() -> Unit) =
    Selector(this).apply(init)

inline fun Fragment.ConstraintLayout(init: ConstraintLayout.() -> Unit) =
    context?.let { ConstraintLayout(it).apply(init) }

inline fun Fragment.LinearLayout(init: LinearLayout.() -> Unit) =
    context?.let { LinearLayout(it).apply(init) }

inline fun Fragment.FrameLayout(init: FrameLayout.() -> Unit) =
    context?.let { FrameLayout(it).apply(init) }


inline fun Fragment.TextView(init: TextView.() -> Unit) =
    context?.let { TextView(it).apply(init) }

inline fun Fragment.Button(init: Button.() -> Unit) =
    context?.let { Button(it).apply(init) }

inline fun Fragment.ImageView(init: ImageView.() -> Unit) =
    context?.let { ImageView(it).apply(init) }

inline fun Fragment.View(init: View.() -> Unit) =
    context?.let { View(it).apply(init) }

inline fun Fragment.ViewFlipper(init: ViewFlipper.() -> Unit) =
    context?.let { ViewFlipper(it).apply(init) }

inline fun Fragment.EditText(init: EditText.() -> Unit) =
    context?.let { EditText(it).apply(init) }

inline fun ViewGroup.LineFeedLayout(init: LineFeedLayout.() -> Unit) =
    LineFeedLayout(context).apply(init).also { addView(it) }

inline fun Context.LineFeedLayout(init: LineFeedLayout.() -> Unit): LineFeedLayout =
    LineFeedLayout(this).apply(init)

inline fun Fragment.LineFeedLayout(init: LineFeedLayout.() -> Unit) =
    context?.let { LineFeedLayout(it).apply(init) }

//</editor-fold>

//<editor-fold desc="View extend field">
inline var View.layout_id: String
    get() {
        return ""
    }
    set(value) {
        id = value.toLayoutId()
    }
inline var View.padding_top: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, value.dp, paddingRight, paddingBottom)
    }

inline var View.padding_bottom: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, paddingTop, paddingRight, value.dp)
    }

inline var View.padding_start: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(value.dp, paddingTop, paddingRight, paddingBottom)
    }

inline var View.padding_end: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(paddingLeft, paddingTop, value.dp, paddingBottom)
    }
inline var View.padding: Int
    get() {
        return 0
    }
    set(value) {
        setPadding(value.dp, value.dp, value.dp, value.dp)
    }
inline var View.layout_width: Int
    get() {
        return 0
    }
    set(value) {
        val w = if (value > 0) value.dp else value
        val h = layoutParams?.height ?: 0
        layoutParams = ViewGroup.MarginLayoutParams(w, h)
    }

inline var View.layout_height: Int
    get() {
        return 0
    }
    set(value) {

        val w = layoutParams?.width ?: 0
        val h = if (value > 0) value.dp else value
        layoutParams = ViewGroup.MarginLayoutParams(w, h)
    }

inline var View.alignParentStart: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
            (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
                addRule(index, i)
            }
            addRule(RelativeLayout.ALIGN_PARENT_START, RelativeLayout.TRUE)
        }
    }

inline var View.alignParentEnd: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
            (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
                addRule(index, i)
            }
            addRule(RelativeLayout.ALIGN_PARENT_END, RelativeLayout.TRUE)
        }
    }

inline var View.centerVertical: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
            (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
                addRule(index, i)
            }
            addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE)
        }
    }

inline var View.centerInParent: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        layoutParams = RelativeLayout.LayoutParams(layoutParams.width, layoutParams.height).apply {
            (layoutParams as? RelativeLayout.LayoutParams)?.rules?.forEachIndexed { index, i ->
                addRule(index, i)
            }
            addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
        }
    }

inline var View.weight: Float
    get() {
        return 0f
    }
    set(value) {
        layoutParams =
            LinearLayout.LayoutParams(layoutParams.width, layoutParams.height).also { it ->
                it.gravity = (layoutParams as? LinearLayout.LayoutParams)?.gravity ?: -1
                it.weight = value
            }
    }
inline var View.layout_gravity: Int
    get() {
        return -1
    }
    set(value) {
        layoutParams =
            LinearLayout.LayoutParams(layoutParams.width, layoutParams.height).also { it ->
                it.weight = (layoutParams as? LinearLayout.LayoutParams)?.weight ?: 0f
                it.gravity = value
            }
    }

inline var View.start_toStartOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            startToStart = value.toLayoutId()
            startToEnd = -1
        }
    }

inline var View.start_toEndOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            startToEnd = value.toLayoutId()
            startToStart = -1
        }
    }

inline var View.top_toBottomOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            topToBottom = value.toLayoutId()
            topToTop = -1
        }
    }

inline var View.top_toTopOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            topToTop = value.toLayoutId()
            topToBottom = -1
        }
    }

inline var View.end_toEndOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            endToEnd = value.toLayoutId()
            endToStart = -1
        }
    }

inline var View.end_toStartOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            endToStart = value.toLayoutId()
            endToEnd = -1
        }
    }

inline var View.bottom_toBottomOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            bottomToBottom = value.toLayoutId()
            bottomToTop = -1
        }
    }

inline var View.bottom_toTopOf: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            bottomToTop = value.toLayoutId()
            bottomToBottom = -1
        }
    }

inline var View.horizontal_chain_style: Int
    get() {
        return -1
    }
    set(value) {
        layoutParams = layoutParams.append {
            horizontalChainStyle = value
        }
    }

inline var View.vertical_chain_style: Int
    get() {
        return -1
    }
    set(value) {
        layoutParams = layoutParams.append {
            verticalChainStyle = value
        }
    }

inline var View.horizontal_bias: Float
    get() {
        return -1f
    }
    set(value) {
        layoutParams = layoutParams.append {
            horizontalBias = value
        }
    }
inline var View.dimension_radio: String
    get() {
        return ""
    }
    set(value) {
        layoutParams = layoutParams.append {
            dimensionRatio = value
        }
    }

inline var View.vertical_bias: Float
    get() {
        return -1f
    }
    set(value) {
        layoutParams = layoutParams.append {
            verticalBias = value
        }
    }

inline var View.center_horizontal: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        start_toStartOf = parent_id
        end_toEndOf = parent_id
    }

inline var View.center_vertical: Boolean
    get() {
        return false
    }
    set(value) {
        if (!value) return
        top_toTopOf = parent_id
        bottom_toBottomOf = parent_id
    }

inline var View.align_vertical_to: String
    get() {
        return ""
    }
    set(value) {
        top_toTopOf = value
        bottom_toBottomOf = value
    }

inline var View.align_horizontal_to: String
    get() {
        return ""
    }
    set(value) {
        start_toStartOf = value
        end_toEndOf = value
    }

inline var View.background_color: String
    get() {
        return ""
    }
    set(value) {
        setBackgroundColor(Color.parseColor(value))
    }

inline var View.background_res: Int
    get() {
        return -1
    }
    set(value) {
        setBackgroundResource(value)
    }

inline var View.margin_top: Int
    get() {
        return -1
    }
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            topMargin = value.dp
        }
    }

inline var View.margin_bottom: Int
    get() {
        return -1
    }
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            bottomMargin = value.dp
        }
    }

inline var View.margin_start: Int
    get() {
        return -1
    }
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            MarginLayoutParamsCompat.setMarginStart(this, value.dp)
        }
    }

inline var View.margin_end: Int
    get() {
        return -1
    }
    set(value) {
        (layoutParams as? ViewGroup.MarginLayoutParams)?.apply {
            MarginLayoutParamsCompat.setMarginEnd(this, value.dp)
        }
    }

inline var View.layout_visibility: Int
    get() {
        return -1
    }
    set(value) {
        visibility = value
    }

inline var View.bindLiveData: LiveDataBinder?
    get() {
        return null
    }
    set(value) {
        observe(value?.liveData) {
            value?.action?.invoke(it)
        }
    }

inline var View.bind: Binder?
    get() {
        return null
    }
    set(value) {
        value?.action?.invoke(this, value.data )
    }

inline var ImageView.src: Int
    get() {
        return -1
    }
    set(value) {
        setImageResource(value)
    }

inline var TextView.textRes: Int
    get() {
        return -1
    }
    set(value) {
        setText(value)
    }

inline var TextView.textStyle: Int
    get() {
        return -1
    }
    set(value) = setTypeface(typeface, value)

inline var TextView.textColor: String
    get() {
        return ""
    }
    set(value) {
        setTextColor(Color.parseColor(value))
    }

inline var TextView.fontFamily: Int
    get() {
        return 1
    }
    set(value) {
        typeface = ResourcesCompat.getFont(context, value)
    }

inline var TextView.onTextChange: TextWatcher
    get() {
        return TextWatcher()
    }
    set(value) {
        val textWatcher = object : android.text.TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                value.afterTextChanged.invoke(s)
            }

            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
                value.beforeTextChanged.invoke(text, start, count, after)
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                value.onTextChanged.invoke(text, start, before, count)
            }
        }
        addTextChangedListener(textWatcher)
    }

inline var Button.textAllCaps: Boolean
    get() {
        return false
    }
    set(value) {
        isAllCaps = value
    }

inline var ConstraintHelper.referenceIds: String
    get() {
        return ""
    }
    set(value) {
        referencedIds = value.split(",").map { it.toLayoutId() }.toIntArray()
    }


var View.onClick: (View) -> Unit
    get() {
        return {}
    }
    set(value) {
        setOnClickListener { v -> value(v) }
    }

//</editor-fold>


//<editor-fold desc="View layout constant">
val match_parent = ViewGroup.LayoutParams.MATCH_PARENT
val wrap_content = ViewGroup.LayoutParams.WRAP_CONTENT

val visible = View.VISIBLE
val gone = View.GONE
val invisible = View.INVISIBLE

val horizontal = LinearLayout.HORIZONTAL
val vertical = LinearLayout.VERTICAL

val bold = Typeface.BOLD
val normal = Typeface.NORMAL
val italic = Typeface.ITALIC
val bold_italic = Typeface.BOLD_ITALIC

val gravity_center = Gravity.CENTER
val gravity_left = Gravity.LEFT
val gravity_right = Gravity.RIGHT
val gravity_bottom = Gravity.BOTTOM
val gravity_top = Gravity.TOP
val gravity_center_horizontal = Gravity.CENTER_HORIZONTAL
val gravity_center_vertical = Gravity.CENTER_VERTICAL

val scale_fix_xy = ImageView.ScaleType.FIT_XY
val scale_center_crop = ImageView.ScaleType.CENTER_CROP
val scale_center = ImageView.ScaleType.CENTER
val scale_center_inside = ImageView.ScaleType.CENTER_INSIDE
val scale_fit_center = ImageView.ScaleType.FIT_CENTER
val scale_fit_end = ImageView.ScaleType.FIT_END
val scale_matrix = ImageView.ScaleType.MATRIX
val scale_fit_start = ImageView.ScaleType.FIT_START


val spread = ConstraintLayout.LayoutParams.CHAIN_SPREAD
val packed = ConstraintLayout.LayoutParams.CHAIN_PACKED
val spread_inside = ConstraintLayout.LayoutParams.CHAIN_SPREAD_INSIDE

val parent_id = "0"
//</editor-fold>

//<editor-fold desc="layout helper function">
val Int.dp: Int
    get() {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            this.toFloat(),
            Resources.getSystem().displayMetrics
        ).toInt()
    }

fun ViewGroup.MarginLayoutParams.toConstraintLayoutParam() =
    ConstraintLayout.LayoutParams(width, height).also { it ->
        it.topMargin = this.topMargin
        it.bottomMargin = this.bottomMargin
        it.marginStart = this.marginStart
        it.marginEnd = this.marginEnd
    }

fun ViewGroup.LayoutParams.append(set: ConstraintLayout.LayoutParams.() -> Unit) =
    (this as? ConstraintLayout.LayoutParams)?.apply(set)
        ?: (this as? ViewGroup.MarginLayoutParams)?.toConstraintLayoutParam()?.apply(set)


fun String.toLayoutId(): Int {
    var id = java.lang.String(this).bytes.sum()
    if (id == 48) id = 0
    return id
}

fun <T : View> View.find(id: String): T? = findViewById(id.toLayoutId())

fun <T> View.observe(liveData: LiveData<T>?, action: (T) -> Unit) {
    (context as? LifecycleOwner)?.let { owner ->
        liveData?.observe(owner, Observer { action(it) })
    }
}

//</editor-fold>


//<editor-fold desc="listener helper class">
class TextWatcher(
    var beforeTextChanged: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) -> Unit = { _, _, _, _ -> },
    var onTextChanged: (
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) -> Unit = { _, _, _, _ -> },
    var afterTextChanged: (text: Editable?) -> Unit = {}
)

fun textWatcher(init: TextWatcher.() -> Unit): TextWatcher = TextWatcher().apply(init)

/**
 * helper class for data binding
 */
class LiveDataBinder(var liveData: LiveData<*>? = null, var action: ((Any?) -> Unit)? = null)

fun liveDataBinder(liveData: LiveData<*>?, init: LiveDataBinder.() -> Unit): LiveDataBinder =
    LiveDataBinder(liveData).apply(init)

class Binder(var data: Any?, var action: ((View, Any?) -> Unit)? = null)


//</editor-fold>