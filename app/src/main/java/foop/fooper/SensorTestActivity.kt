package foop.fooper


import android.content.Context
import android.graphics.Color
import android.graphics.DashPathEffect
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.Legend.LegendForm
import com.github.mikephil.charting.components.LimitLine
import com.github.mikephil.charting.components.LimitLine.LimitLabelPosition
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils


class SensorTestActivity : AppCompatActivity(), SensorEventListener {
    private var mSensorManager: SensorManager? = null
    private var mmAccelerometer: Sensor? = null
    private val sensors: Sensor? = null
    private var mChart: LineChart? = null
    private var thread: Thread? = null
    private var plotData = true
    private var type: String? = null
    private var mAccelerometer: Sensor? = null
    private var mAccelerometer2: Sensor? = null
    private var mWindowManager: WindowManager? = null;
    val sinEntries = ArrayList<Entry>() // List to store data-points of sine curve
    val cosEntries = ArrayList<Entry>()
    val zAxies = ArrayList<Entry>()// List to store data-points of cosine curve
    val values = java.util.ArrayList<Entry>()
    private var counter = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.chart)
        mChart = findViewById<LineChart>(R.id.chart1)
        mWindowManager = getWindow().getWindowManager()
        // enable description text
        mChart!!.description.isEnabled = true

        // enable touch gestures
        mChart!!.setTouchEnabled(true)

        // enable scaling and dragging
        mChart!!.isDragEnabled = true
        mChart!!.setScaleEnabled(true)
        mChart!!.setDrawGridBackground(false)

        // if disabled, scaling can be done on x- and y-axis separately
        mChart!!.setPinchZoom(true)

        // set an alternative background color
        mChart!!.setBackgroundColor(Color.BLACK)

        val data = LineData()
        data.setValueTextColor(Color.BLACK)

        val leftAxis = mChart!!.axisLeft
        val rights = mChart!!.axisRight

        leftAxis.textColor = Color.WHITE
        leftAxis.setDrawGridLines(false)
        leftAxis.setDrawGridLines(true)

        mSensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        type = intent.getStringExtra("type")
        if (type.equals("mAccelerometer")) {
            leftAxis.axisMinimum = -50f
            leftAxis.axisMaximum = 50f
            leftAxis.spaceMin = 100f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        } else if (type.equals("Gyroscope")) {
            leftAxis.axisMinimum = -50f
            leftAxis.axisMaximum = 50f
            leftAxis.spaceMin = 500f
            leftAxis.labelCount = 4
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE)
        } else if (type.equals("Magnetometer")) {
            leftAxis.axisMinimum = -100f
            leftAxis.axisMaximum = 100f
            leftAxis.spaceMin = 50f
            leftAxis.labelCount = 5
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD)
        } else if (type.equals("Gravity Sensor")) {
            leftAxis.axisMinimum = -10f
            leftAxis.axisMaximum = 10f
            leftAxis.spaceMin = 5f
            leftAxis.labelCount = 5
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_GRAVITY)
        } else if (type.equals("Linear Acceleration")) {
            leftAxis.axisMinimum = -20f
            leftAxis.axisMaximum = 20f
            leftAxis.spaceMax = 5f
            leftAxis.labelCount = 6
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
        } else if (type.equals("Rotation Sensor")) {
            leftAxis.axisMinimum = -500f
            leftAxis.axisMaximum = 500f

            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_ORIENTATION)
        } else if (type.equals("Ambient Temperature")) {
            leftAxis.axisMinimum = -100f
            leftAxis.axisMaximum = 100f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        } else if (type.equals("Proximity Sensor")) {
            linearGraph()
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        } else if (type.equals("Light Sensor")) {

            leftAxis.axisMinimum = -500f
            leftAxis.axisMaximum = 500f
            leftAxis.spaceMax = 100f
            leftAxis.labelCount = 3
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)
        } else if (type.equals("Pressure Sensor")) {
            leftAxis.axisMinimum = -100f
            leftAxis.axisMaximum = 100f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PRESSURE)
        } else if (type.equals("Relative Sensor")) {
            leftAxis.axisMinimum = -100f
            leftAxis.axisMaximum = 100f
            mAccelerometer = mSensorManager!!.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY)
        }
        mSensorManager!!.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)

        val sensors = mSensorManager!!.getSensorList(Sensor.TYPE_ALL)

        for (i in sensors.indices) {
            Log.d(TAG, "onCreate: Sensor " + i + ": " + sensors[i].toString())
        }

        if (mmAccelerometer != null) {
            mSensorManager!!.registerListener(this, mmAccelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        }


        // add empty data
        mChart!!.data = data

        // get the legend (only possible after setting data)
        val l = mChart!!.legend

        // modify the legend ...
        l.form = Legend.LegendForm.LINE
        l.textColor = Color.WHITE

        val xl = mChart!!.xAxis
        xl.textColor = Color.WHITE
        xl.setDrawGridLines(true)
        xl.setAvoidFirstLastClipping(true)
        xl.isEnabled = true

        val rightAxis = mChart!!.axisRight
        rightAxis.isEnabled = false

        mChart!!.axisLeft.setDrawGridLines(true)
        mChart!!.xAxis.setDrawGridLines(true)
        mChart!!.setDrawBorders(false)

        feedMultiple()

    }

    private fun addEntry(event: SensorEvent) {

        val data = mChart!!.data


        if (data != null) {
            val chartData = LineData()
            var set: ILineDataSet? = data.getDataSetByIndex(0)
            var set2: ILineDataSet? = data.getDataSetByIndex(0)
            // set.addEntry(...); // can be called as well

            if (set == null) {
                set = createSet()
                data.addDataSet(set)
            }
            if (set2 == null) {
                set2 = createSet()
                data.addDataSet(set2)
            }

            //data.addEntry(new Entry(set.getEntryCount(), (float) (Math.random() * 80) + 10f), 0);
            data.addEntry(Entry(set.entryCount.toFloat(), event.values[0]), 0)
            data.notifyDataChanged()
            sinEntries.add(Entry(set.entryCount.toFloat(), event.values[0]))
            cosEntries.add(Entry(set.entryCount.toFloat(), event.values[1]))
            zAxies.add(Entry(set.entryCount.toFloat(), event.values[2]))

            if (type.equals("Gyroscope")||type.equals("Rotation Sensor")) {
                val sinSet = LineDataSet(sinEntries, "roll")
                val cosSet = LineDataSet(cosEntries, "pitch")
                val zaxies = LineDataSet(zAxies, "yaw")
                setChart(zaxies, cosSet, sinSet, data.entryCount.toFloat())
            } else {
                val sinSet = LineDataSet(sinEntries, "X")
                val cosSet = LineDataSet(cosEntries, "Y")
                val zaxies = LineDataSet(zAxies, "Z")
                setChart(zaxies, cosSet, sinSet, data.entryCount.toFloat())
            }


        }
    }

    private fun setChart(zaxies: LineDataSet, cosSet: LineDataSet, sinSet: LineDataSet ,moveTo:Float) {
        val dataSets = ArrayList<ILineDataSet>()
        zaxies.lineWidth = 0.5f
        zaxies.color = Color.RED
        zaxies.isHighlightEnabled = false
        zaxies.setDrawValues(false)
        zaxies.setDrawCircles(false)
        zaxies.mode = LineDataSet.Mode.CUBIC_BEZIER
        zaxies.cubicIntensity = 0.2f
// Adding colors to different plots

        cosSet.lineWidth = 0.5f
        cosSet.color = Color.GREEN
        cosSet.isHighlightEnabled = false
        cosSet.setDrawValues(false)
        cosSet.setDrawCircles(false)
        cosSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        cosSet.cubicIntensity = 0.2f

        sinSet.lineWidth = 0.5f
        sinSet.color = Color.YELLOW
        sinSet.isHighlightEnabled = false
        sinSet.setDrawValues(false)
        sinSet.setDrawCircles(false)
        sinSet.mode = LineDataSet.Mode.CUBIC_BEZIER
        sinSet.cubicIntensity = 0.2f

        if (type.equals("Light Sensor") || type.equals("Proximity Sensor")) {
            dataSets.add(sinSet)
        } else {
            dataSets.add(sinSet)
            dataSets.add(cosSet)
            dataSets.add(zaxies)
        }

        mChart!!.setData(LineData(dataSets))
        // let the mChart know it's data has changed
        mChart!!.notifyDataSetChanged()

        // limit the number of visible entries
        mChart!!.setVisibleXRangeMaximum(100f)
        // mChart.setVisibleYRange(30, AxisDependency.LEFT);

        // move to the latest entry
        mChart!!.moveViewToX(moveTo)
    }

    private fun createSet(): LineDataSet {

        val set = LineDataSet(null, "Dynamic Data")
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.lineWidth = 3f
        set.color = Color.MAGENTA
        set.isHighlightEnabled = false
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.cubicIntensity = 0.2f
        return set
    }

    private fun createSet2(): LineDataSet {

        val set = LineDataSet(null, "Dynamic Data")
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.lineWidth = 3f
        set.color = Color.GREEN
        set.isHighlightEnabled = false
        set.setDrawValues(false)
        set.setDrawCircles(false)
        set.mode = LineDataSet.Mode.CUBIC_BEZIER
        set.cubicIntensity = 0.2f
        return set
    }

    private fun feedMultiple() {

        if (thread != null) {
            thread!!.interrupt()
        }

        thread = Thread(Runnable {
            while (true) {
                plotData = true
                try {
                    Thread.sleep(10)
                } catch (e: InterruptedException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }

            }
        })

        thread!!.start()
    }

    override fun onPause() {
        super.onPause()

        if (thread != null) {
            thread!!.interrupt()
        }
        mSensorManager!!.unregisterListener(this)

    }

    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
        // Do something here if sensor accuracy changes.
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (plotData) {
            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_ORIENTATION) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_MAGNETIC_FIELD) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_RELATIVE_HUMIDITY) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_PRESSURE) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_LIGHT) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                setDataLinearChat(45, 180f, event.values[0])
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_LINEAR_ACCELERATION) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
                addEntry(event)
                plotData = false
            } else if (event.sensor.type == Sensor.TYPE_GRAVITY) {
                addEntry(event)
                plotData = false
            }

        }
    }


    override fun onResume() {
        super.onResume()
        mSensorManager!!.registerListener(this, mmAccelerometer, SensorManager.SENSOR_DELAY_GAME)
    }

    override fun onDestroy() {
        mSensorManager!!.unregisterListener(this@SensorTestActivity)
        thread!!.interrupt()
        super.onDestroy()
    }

    companion object {
        private val TAG = "SensorTestActivity"
    }


    private fun linearGraph() {
        mChart!!.setBackgroundColor(Color.BLACK)
        mChart!!.getDescription().setEnabled(false)

        mChart!!.setTouchEnabled(true)

        //mChart!!.setOnChartValueSelectedListener(this)
        mChart!!.setDrawGridBackground(false)

        mChart!!.setDragEnabled(true)
        mChart!!.setScaleEnabled(true)

        mChart!!.setPinchZoom(true)


        var xAxis: XAxis


        xAxis = mChart!!.getXAxis()
        xAxis.enableGridDashedLine(2f, 0f, 0f)
        var yAxis: YAxis
        yAxis = mChart!!.getAxisLeft()
        mChart!!.getAxisRight().setEnabled(false)
        yAxis.enableGridDashedLine(5f, 0f, 0f)
        yAxis.axisMinimum = 0f
        yAxis.axisMaximum = 6f
        yAxis.spaceMax = 1f
        yAxis.labelCount = 3
        val llXAxis = LimitLine(9f, "Index 10")
        llXAxis.lineWidth = 1f
        /* llXAxis.enableDashedLine(10f, 10f, 0f)*/
        llXAxis.labelPosition = LimitLabelPosition.RIGHT_BOTTOM
        llXAxis.textSize = 10f
        val ll1 = LimitLine(10f, "Upper Limit")
        ll1.lineWidth = 0f
        /*  ll1.enableDashedLine(10f, 10f, 0f)*/
        ll1.labelPosition = LimitLabelPosition.RIGHT_TOP
        ll1.textSize = 5f
        val ll2 = LimitLine(0f, "Lower Limit")
        ll2.lineWidth = 0f
        ll2.labelPosition = LimitLabelPosition.RIGHT_BOTTOM
        ll2.textSize = 5f
        yAxis!!.setDrawLimitLinesBehindData(true)
        xAxis!!.setDrawLimitLinesBehindData(true)
        yAxis.addLimitLine(ll1)
        yAxis.addLimitLine(ll2)
        setDataLinearChat(20, 180f, 0f)
        mChart!!.animateX(500)
        mChart!!.setBackgroundColor(Color.BLACK)
        val l: Legend = mChart!!.getLegend()
        l.form = LegendForm.LINE
    }

    private fun setDataLinearChat(count: Int, range: Float, newValue: Float) {
        counter++
        val dataSets = ArrayList<ILineDataSet>()
        values.add(Entry(counter.toFloat(), newValue, resources.getDrawable(R.drawable.gradient)))
        val set1: LineDataSet
        if (mChart!!.getData() != null && mChart!!.getData().getDataSetCount() > 0) {
            set1 = mChart!!.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = values
            set1.notifyDataSetChanged()
            mChart!!.getData().notifyDataChanged()
            mChart!!.notifyDataSetChanged()
            mChart!!.moveViewToX(set1.entryCount.toFloat())
        } else { // create a dataset and give it a type
            set1 = LineDataSet(values, "Proximity")
            set1.setDrawIcons(false)
            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f)
            // black lines and points
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)
            // line thickness and point size
            set1.lineWidth = 1f
            set1.circleRadius = 3f
            // draw points as solid circles
            set1.setDrawCircleHole(false)
            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f
            // text size of values
            set1.valueTextSize = 9f
            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)
            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter = IFillFormatter { dataSet, dataProvider -> mChart!!.getAxisLeft().getAxisMinimum() }
            // set color of filled area
            if (Utils.getSDKInt() >= 18) { // drawables only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(this, R.drawable.gradient)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }
            val dataSets = java.util.ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets
            // create a data object with the data sets
            val data = LineData(dataSets)
            // set data
            mChart!!.setData(data)
        }
    }
}