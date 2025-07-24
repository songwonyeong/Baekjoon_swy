fun main() {
    // 처음에 다 가위냈을 때의 결과 K
    var K = readln().toInt()
    // 처음에 다 가위낸 상태로 시작
    val RSP = "2".repeat(100).toCharArray()
    // 여기에 머신의 패턴을 기록한다
    val answer = StringBuilder()
    repeat(100) {
        // 가위대신 바위내기
        RSP[it] = '0'
        // 결과 확인
        println("? ${RSP.joinToString("")}")
        System.out.flush()
        val result = readln().toInt()

        // 값이 증가 -> 바위로 이길 수 있음 (머신은 가위를 냈음)
        // 값 유지 -> 보로 이길 수 있음 (머신은 바위를 냈음)
        // 값이 감소 -> 가위로 이길 수 있음 (머신은 보를 냈음)
        when(result) {
            K + 1 -> {
                answer.append('2')
                K++
            }
            K -> {
                RSP[it] = '5'
                answer.append('0')
                K++
            }
            K - 1 -> {
                RSP[it] = '2'
                answer.append('5')
            }
        }
    }
    println("! $answer")
    System.out.flush()
}