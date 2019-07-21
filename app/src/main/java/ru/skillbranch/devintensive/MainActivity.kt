package ru.skillbranch.devintensive

import android.graphics.Color
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import ru.skillbranch.devintensive.models.Bender

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var benderImage: ImageView
    lateinit var textTxt: TextView
    lateinit var messageEt: EditText
    lateinit var sendBtn: ImageView

    lateinit var benderObj: Bender

    /**
     * вызывается при первом создании или перезапуске Activity
     * задаётся внешний вид Activity (UI) через метод setContentView().
     * инициализируются представления и модели
     * представления связываются с необходимыми данными и ресурсами
     *
     * Этот метод также предоставляет Bundle, содержащий ранее сохранённое состояние Actiivity, если оно было.
     *
     * Всегда сопровождается вызовом onStart()
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        benderImage = iv_bender
        textTxt = tv_text
        messageEt = et_message
        sendBtn = iv_send

        messageEt.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) sendBtn.performClick()
            false
        }

        val status = savedInstanceState?.getString("STATUS") ?: Bender.Status.NORMAL.name
        val question = savedInstanceState?.getString("QUESTION") ?: Bender.Question.NAME.name
        benderObj = Bender(Bender.Status.valueOf(status), Bender.Question.valueOf(question))

        Log.d("DI_MainActivity", "onCreate $status $question")
        val (r, g, b) = benderObj.status.color
        benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)

        textTxt.text = benderObj.askQuestion()
        sendBtn.setOnClickListener(this)
    }

    /**
     * вызывается если Activity возвращается в приоритетный режим после вызова onStop(),
     * т.е. вызывается после того, как Activity была остановлена и снова была запущена пользователем.
     *
     * Всегда сопровождается вызовом метода onStart().
     *
     * используется для специальных действий, которые должны выполняться только при повторном запуске Activity
     */
    override fun onRestart() {
        super.onRestart()
        Log.d("DI_MainActivity", "onRestart")
    }

    /**
     * При выхове onStart() UI еще не виден пользователю, но вскоре будет виден,
     * вызывается непосредственно перед тем, как Activity становится видимой пользователю.
     *
     * чтение из базы данных
     * запуск сложной анимации
     * запуск потоков, отслеживания показаний датчиков, запросов к GPS, сервисов или других процессов,
     * которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Затем следует onResume(), если Activity выходит на передний план
     */
    override fun onStart() {
        super.onStart()
        Log.d("DI_MainActivity", "onStart")
    }

    /**
     * вызывается, когда Activity начнет взаимодействовать с пользователем.
     *
     * запуск воспроизведения анимации, аудио и видео
     * регистрации любых BroadcastReceiver или других процессов, которые вы освободили/приостановили в onPause()
     * выполнение любых другие инициализации, которые должны происходить, когда Activity вновь активна (камера).
     *
     * Тут должен быть максимально лёгкий и быстрый код, чтобы приложение оставалось отзывчивым
     */
    override fun onResume() {
        super.onResume()
        Log.d("DI_MainActivity", "onResume")
    }

    /**
     * вызывается после сворачивания текущей активности или перехода к новому.
     * От onPause() можно перейти к вызову либо onResume(), либо onStop().
     *
     * остановка анимации, аудио и видео
     * сохранение состояния пользовательского ввода (легкие процессы)
     * сохранение в DB если данные должны быть доступны в новой Activity
     * остановка сервисов, подписок, BroadcastReceiver
     *
     * Тут должен быть максимально лёгкий и быстрый код, чтобы приложение оставалось отзывчивым
     */
    override fun onPause() {
        super.onPause()
        Log.d("DI_MainActivity", "onPause")
    }

    /**
     * вызывается, когда Activity становится невидимым для пользователя.
     * Это может произойти при её уничтожении, или если была запущена другая Activity (существующая или новая),
     * перекрывшая окно текущей Activity.
     *
     * запись в базу данных
     * приостановка сложной анимации
     * приостановка потоков, отслеживания показаний датчиков, запросов к GPS, таймеров,
     * сервисов или других процессов, которые нужны исключительно для обновления пользовательского интерфейса
     *
     * Не вызывается при выызове finish() у Activity
     */
    override fun onStop() {
        super.onStop()
        Log.d("DI_MainActivity", "onStop")
    }

    /**
     * вызывается по окончании работы Activity (пользователь закрывает приложение через клавишу back,
     * или удаляет из списка активных приложений), при вызове метода finish() или в случае, когда система уничтожает
     * этот экземпляр активности для освобождения ресурсов.
     *
     * высвобождение ресурсов
     * дополнительная перестраховка если ресурсы не были выгружены или процессы не были остановлены ранее
     */
    override fun onDestroy() {
        super.onDestroy()
        Log.d("DI_MainActivity", "onDestroy")
    }

    /**
     * сохраняет состояние представления в Bundle
     * для API level < 28 (Android P) этот метод будет выполняться до onStop() и нет никаких гарантий отностительно того,
     * произойдёт ли это до или после onPause()
     *
     * Для API level >= 28 будет вызван после onStop()
     *
     * Не будет вызван, если Activity будет явно закрыто пользователем при нажатии на системную клавишу back
     */
    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putString("STATUS", benderObj.status.name)
        outState?.putString("QUESTION", benderObj.question.name)
        Log.d("DI_MainActivity", "onSaveInstanceState ${benderObj.status.name} ${benderObj.question.name}")
    }

    override fun onClick(v: View?) {
        if (v?.id == R.id.iv_send) {
            val (phrase, color) = benderObj.listenAnswer(messageEt.text.toString().toLowerCase())
            messageEt.setText("")
            val (r, g, b) = color
            benderImage.setColorFilter(Color.rgb(r, g, b), PorterDuff.Mode.MULTIPLY)
            textTxt.text = phrase
        }
    }
}
