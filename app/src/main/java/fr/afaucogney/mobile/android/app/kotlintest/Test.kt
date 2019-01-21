package fr.afaucogney.mobile.android.app.kotlintest

interface MyCallBack {
    fun onSuccess(result: Int)
    fun onError(code: Int)
}

class MainClass {
    fun execute(callBack: MyCallBack) {
        MyDependency().send(1, object : MyCallBack {
            override fun onSuccess(result: Int) {
                MySecondDependency().doStuff()
                callBack.onSuccess(result)
            }

            override fun onError(code: Int) {
                callBack.onError(code)
            }
        })
    }
}

class MyDependency {
    fun send(entry: Int, callBack: MyCallBack) {
        when (entry) {
            1 -> callBack.onSuccess(0)
            else -> callBack.onError(entry)
        }
    }
}

class MySecondDependency {
    fun doStuff() {
    }
}