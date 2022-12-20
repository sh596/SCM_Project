package com.example.dudeulimproject.utils

import com.example.dudeulimproject.data.RequestInterViewData
import com.example.dudeulimproject.data.RequestInterViewSeeMoreData
import com.example.dudeulimproject.data.User

object DummyData {
    val DUMMY_USER_1 =
        User("", "kd531585@gmail.com", "8ed3ab06-e5db-4452-b94c-116869844e31", "480b711c-7cbf-44fd-b267-023ca251d272", "개발자", "박금혁")
    val DUMMY_USER_2 =
        User("", "kd531585@gmail.com", "7d4062a2-eb23-47b8-89cd-f356087a021d", "9350c193-056f-49b6-9478-137bde19c448", "개발자", "김병주")
    val DUMMY_REQUEST_INTERVIEW_SEE_MORE_1 = RequestInterViewSeeMoreData(
        "001",
        "목탁 조이고",
        "12/10 10:00",
        "선린인터넷고등학교|37.5428|126.9672",
        "offline",
        null,
        DUMMY_USER_1,
        DUMMY_USER_2
    )
    val DUMMY_REQUEST_INTERVIEW_SEE_MORE_2 = RequestInterViewSeeMoreData(
        "002",
        "백엔드 개발자 박금혁의 이야기",
        "",
        "",
        "online",
        null,
        DUMMY_USER_2,
        DUMMY_USER_1)
    val DUMMY_PROCEED_INTERVIEW_SEE_MORE_1 = RequestInterViewSeeMoreData(
        "001",
        "백엔드 개발자 박금혁의 이야기",
        "12/29",
        "",
        "online",
        listOf(""),
        DUMMY_USER_1,
        DUMMY_USER_2
    )
    val DUMMY_PROCEED_INTERVIEW_SEE_MORE_2 = RequestInterViewSeeMoreData(
        "002",
        "백엔드 개발자 박금혁의 이야기",
        "12/21",
        "",
        "offline",
        listOf(""),
        DUMMY_USER_2,
        DUMMY_USER_1)


    val DUMMY_REQUEST_INTERVIEW_1 = RequestInterViewData(
        "001",
        "480b711c-7cbf-44fd-b267-023ca251d272",
        "12-19",
        "개발자 박금혁에 이야기를 들려드립니다",
        "online",
        true,
        DUMMY_USER_1
    )
    val DUMMY_REQUEST_INTERVIEW_2 = RequestInterViewData(
        "002",
        "b9a95495-e6f7-40ee-a2ff-4f3da5690ea1",
        "12-21",
        "쇼콜라티에 인터뷰",
        "offline",
        false,
        DUMMY_USER_2
    )
    val DUMMY_PROCEED_INTERVIEW_1 = RequestInterViewData(
        "001",
        "480b711c-7cbf-44fd-b267-023ca251d272",
        "12-19",
        "개발자 박금혁에 이야기를 들려드립니다",
        "online",
        true,
        DUMMY_USER_1
    )

    val DUMMY_PROCEED_INTERVIEW_2 = RequestInterViewData(
        "002",
        "480b711c-7cbf-44fd-b267-023ca251d272",
        "12-19",
        "개발자 박금혁에 이야기를 들려드립니다",
        "offline",
        false,
        DUMMY_USER_2
    )

}