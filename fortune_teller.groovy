def 姓筆劃 = 17
def 名字筆劃1
def 名字筆劃2


def isGood(a) {
    def good = [1, 3, 5, 6, 7, 8, 11, 13, 15, 16, 17, 18, 21, 23, 24, 25, 29, 31, 32, 33, 35, 37, 39, 41, 45, 47, 48, 50, 52, 57, 63, 65, 67, 68, 81]
    if (a in good) {
        return true
    } else {
        return false
    }
}


def isSum2Good(a, b) {
    if (isGood(a + b)) {
        return true
    } else {
        return false
    }
}

def isSum3Good(a, b, c) {
    if (isGood(a + b + c)) {
        return true
    } else {
        return false
    }
}

def 算成功運(sky, person) {
    if (getSuccessTF(sky, person)) {
        return '(good)'
    } else {
        return '(bad)'
    }
}

def getSuccessTF(sky, person) {
    if (person == 1 || person == 2) {
        if (sky == 1 || sky == 2 || sky == 3 || sky == 4 || sky == 9 || sky == 10) return true
    }
    if (person == 3 || person == 4) {
        if (sky == 1 || sky == 2 || sky == 5 || sky == 6) return true
    }
    if (person == 5 || person == 6) {
        return true
    }
    if (person == 7 || person == 8) {
        if (sky == 5 || sky == 6 || sky == 9 || sky == 10) return true
    }
    return false
}

def 算基礎運(person, ground) {
    if (getBaseTF(person, ground)) {
        return '(good)'
    } else {
        return '(bad)'
    }
}

def getBaseTF(person, ground) {
    if (person == 1 || person == 2) {
        if (ground == 1 || ground == 2 || ground == 3 || ground == 4 || ground == 5 || ground == 6) return true
    }
    if (person == 3 || person == 4) {
        if (ground == 1 || ground == 2 || ground == 3 || ground == 4 || ground == 5 || ground == 6) return true
    }
    if (person == 5 || person == 6) {
        if (ground == 3 || ground == 4 || ground == 5 || ground == 6 || ground == 7 || ground == 8) return true
    }
    if (person == 7 || person == 8) {
        if (ground == 5 || ground == 6) return true
    }
    return false
}

def 筆劃轉換成五行(num) {
    if (num == 1 || num == 2) return '木'
    if (num == 3 || num == 4) return '火'
    if (num == 5 || num == 6) return '土'
    if (num == 7 || num == 8) return '金'
    if (num == 9 || num == 10) return '水'
}


def get五行3(person, ground) {
    def all = "土${person}${ground}"
    if (all == '土火木' || all == '土火火' || all == '土火土' || all == '土土火' || all == '土土土' || all == '土土金' || all == '土金土' || all == '土金金' || all == '土金水' || all == '土木火' || all == '土木木') return '(good)'
    return '(bad)'
}

def 算三才(sky, person, ground) {
    def all = 轉換成三才(sky, person, ground)
    if (all == '金土火' || all == '金土土' || all == '金土金' || all == '金金土' || all == '金水金') return true
    return false
}

def 轉換成三才(sky, person, ground) {
    def s = 筆劃轉換成五行(sky)
    def p = 筆劃轉換成五行(person)
    def g = 筆劃轉換成五行(ground)
    def all = "${s}${p}${g}"
    return all
}

def number = 1

for (名字筆劃1 = 1; 名字筆劃1 <= 30; 名字筆劃1++) {
    if (isGood(名字筆劃1) && isSum2Good(姓筆劃, 名字筆劃1)) {
        for (名字筆劃2 = 1; 名字筆劃2 < 30; 名字筆劃2++) {
            if (isGood(名字筆劃2) && isSum2Good(名字筆劃2, 名字筆劃1) && isSum2Good(1, 名字筆劃2)) {
                def 天格 = 1 + 姓筆劃
                def 人格 = 姓筆劃 + 名字筆劃1
                def 地格 = 名字筆劃1 + 名字筆劃2
                def 外格 = 1 + 名字筆劃2
                def 總格 = 姓筆劃 + 名字筆劃1 + 名字筆劃2
//                println "天格:${天格} 人格:${人格} 地格:${地格} 外格:${外格} 總格:${總格}"

                def 天格尾數 = 天格 % 10
                if (天格尾數 == 0) {
                    天格尾數 = 10
                }

                def 人格尾數 = 人格 % 10
                if (人格尾數 == 0) {
                    人格尾數 = 10
                }

                def 地格尾數 = 地格 % 10
                if (地格尾數 == 0) {
                    地格尾數 = 10
                }
                def 成功運 = 算成功運(天格尾數, 人格尾數)
                def 基礎運 = 算基礎運(人格尾數, 地格尾數)
                def 三才 = 算三才(天格尾數, 人格尾數, 地格尾數)

//                if (成功運) {
//                    println "成功運 OK"
//                }
//
//                if (基礎運) {
//                    println "基礎運 OK"
//                }
//
//                if (三才) {
//                    println "三才 OK"
//                }

                if (成功運 && 基礎運 && 三才) {
                    println "[${number}]"
                    number = number + 1
                    println "第一個名字筆劃:${名字筆劃1}, 第二個名字筆劃: ${名字筆劃2}"
                    println "天格:${天格} 人格:${人格} 地格:${地格} 外格:${外格} 總格:${總格}"
                    def all = 轉換成三才(天格尾數, 人格尾數, 地格尾數)
                    println "三才: ${all}"
                }
            }
        }

    }
}



