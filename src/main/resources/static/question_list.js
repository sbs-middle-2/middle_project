// 플러스 마이너스 토쿨 기능
$(document).ready(function () {
  $(".accordion-header").click(function () {
    $(this).next(".accordion-content").slideToggle("slow");
    $(this).find("i").toggleClass("fa-plus fa-minus");
  });
});

// 게시물 검색기능
$(document).ready(function () {
  $("#searchInput").on("keydown", function (e) {
    if (e.key === "Enter") {
      e.preventDefault(); // 엔터 키의 기본 동작(페이지 새로고침) 방지

      var value = $(this).val().toLowerCase();
      var $items = $(".accordion-item");

      $items.hide(); // 모든 항목을 일단 숨김

      if (value.length >= 1) {
        var $matchingItems = $items.filter(function () {
          var itemText = $(this).text().toLowerCase();
          return (
              itemText.includes("작성자:" + value) ||
              itemText.includes("제목:" + value) ||
              itemText.includes("내용:" + value)
          );
        });

        if ($matchingItems.length === 0) {
          Swal.fire({
            icon: "warning",
            title: "검색 결과가 없습니다.",
            text: "다른 검색어를 시도해보세요."
          });
        }

        $matchingItems.show();
      } else {
        $items.show();
      }
    }
  });
});