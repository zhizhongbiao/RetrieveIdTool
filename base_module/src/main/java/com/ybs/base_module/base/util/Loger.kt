import android.util.Log


/**
 *
 * @ProjectName:    AW
 * @Package:        com.aylson.aw.santos.util
 * @ClassName:      Loger
 * @Description:
 * @Author:         TaxiDriverSantos
 * @CreateDate:     2020/3/12   14:05
 * @UpdateUser:     TaxiDriverSantos
 * @UpdateDate:     2020/3/12   14:05
 * @UpdateRemark:
 * @Version:        1.0
 */

const val head = ""
const val DEBUG = true
const val TAG = "Zzb"
const val THRESHOLD = 2 * 1024  // three byte


fun handleLog(level: Int, msg: String, head: String) {

    val stackTrace = Thread.currentThread().stackTrace
    var trace: StackTraceElement? = null

    if (stackTrace.size > 4) {
        trace = stackTrace[4]
    }

    val sb = StringBuilder(80)

    if (trace != null) {
        sb.append("[ ")
        val fileName = trace.fileName

        if (fileName == null) {
            sb.append("(Unknown Source)")
        } else {
            val lineNumber = trace.lineNumber
            sb.append("(")
            sb.append(fileName)
            if (lineNumber >= 0) {
                sb.append(':')
                sb.append(lineNumber)
            }
            sb.append(")")
            sb.append(" ### ")

            val clzName = trace.className
            val lastName = clzName.split(".").last()
            sb.append(lastName + "." + trace.methodName + "()")
        }
        sb.append(" ] ")
        sb.append("printLog --> ")
    }

    sb.append(head)

    sb.append(msg)


    val log = sb.toString()
    val toByteArray = log.toByteArray()
    val len = toByteArray.size

    if (len < THRESHOLD) {
        printSubMsg(level, log)
    } else {
        var count = 0
        for (i in 0 until len step THRESHOLD) {
            count = (len - i).coerceAtMost(THRESHOLD)
            printSubMsg(level, String(toByteArray, i, count))
        }
    }
}

private fun printSubMsg(level: Int, logStr: String) {
    val lines = logStr.split(System.lineSeparator())
    lines.forEach {
        when (level) {
            Log.VERBOSE -> Log.v(TAG, "--**--**-- START --**--**-- $it")
            Log.DEBUG -> Log.d(TAG, "--**--**-- START --**--**-- $it")
            Log.INFO -> Log.i(TAG, "--**--**-- START --**--**-- $it")
            Log.WARN -> Log.w(TAG, "--**--**-- START --**--**-- $it")
            Log.ERROR -> Log.e(TAG, "--**--**-- START --**--**-- $it")
            else -> Log.e(TAG, "--**--**-- START --**--**-- $it")
        }
    }

}


fun v(msg: String) {
    if (DEBUG) {
        handleLog(Log.VERBOSE, msg, head)
    }
}

fun d(msg: String) {
    if (DEBUG) {
        handleLog(Log.DEBUG, msg, head)
    }
}

fun i(msg: String) {
    if (DEBUG) {
        handleLog(Log.INFO, msg, head)
    }
}

fun w(msg: String) {
    if (DEBUG) {
        handleLog(Log.WARN, msg, head)
    }
}

fun e(msg: String) {
    if (DEBUG) {
        handleLog(Log.ERROR, msg, head)
    }
}