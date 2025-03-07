package com.preschool.preschoolhome.common.utils;

public class Const {
    public static final int LOGIN_NO_UID = 2;
    public static final int LOGIN_DIFF_UPW = 3;

    public static final int PARENT = 1; //학부모
    public static final int TEACHER = 2; //관리자 선생님
    public static final int BOSS = 3; //관리자 원장님
    public static final int graduate = 4; //졸업자 학부모
    public static final int FAIL = -1; //실패 응답값-----------
    public static final int SUCCESS = 1; //성공 응답값--------
    public static final int ZERO = 0; // 영향 받은 행이 없을 때나 초기화 시 넣을 더미 값
    public static final int CLASS_HIBISCUS = 1; // 무궁화반
    public static final int CLASS_SUNFLOWER = 2; // 해바라기반
    public static final int CLASS_ROSE = 3; // 장미반
    public static final int CLASS_GENERAL = 4; // 전체 반 총괄
    public static final int GIRL = 0; // 여아
    public static final int BOYS = 1; // 남아
    public static final int STATE_ATTENDING = 0; // 재원 중
    public static final int STATE_GRADUATE = -1; // 원아졸업상태
    public static final int STATE_DROP_OUT = -2; // 원아퇴소상태

    public static final int PAGE_ROWCOUNT = 12; // 원생, 학부모 관리 페이지, 알림장 조회 시 ROWCOUNT
    public static final int NO_INFORMATION = -3; // 해당 정보로 조회 시 조회되는 정보 없음



    public static final int FAKE_IS_DEL = 1; //데이터 DELETE인 척 처리 시
    public static final int IS_DEL_LEVEL = 4; //부모 계정 레벨 졸업부모로 수정

    public static final int ROW_COUNT = 6; // 활동 앨범 상세 페이지
    public static final int MAIN_ROW_COUNT = 3; // 메인페이지 활동 앨범 페이징 값

    public static final int NOTICE_COUNT_PER_PAGE=10; //게시판 갯수 10개 제한

    public static final int PICS_FAIL = -5;//유치원 소식 사진 작성 실패
    public static final int FIX_MAX = -4;//유치원 소식 상단 고정 3개 다 참
    public static final int PICS_UP_FAIL = -3;//유치원 소식 사진 삭제 실패

    public static final int MEMORY_PIC = 20;//추억앨범 사진 수
    public static final int FULL_NOTICE_PIC = 10;//유치원 소식 사진 수
    public static final int NOTICE_PIC = 5;//알림장 사진 수
    public static final int ALBUM_PIC = 20;//활동 앨범 사진 수

    public static final int FILE_FAIL = -1;//업로드 파일이 잘못됨 다른 파일 업로드 필요
}
