// 플러스 마이너스 토쿨 기능
$(document).ready(function () {
  $(".accordion-header").click(function () {
    $(this).next(".accordion-content").slideToggle("slow");
    $(this).find("i").toggleClass("fa-plus fa-minus");
  });
});

// 게시물 검색기능
$(document).ready(function () {
  $("#searchInput").on("keyup", function () {
    var value = $(this).val().toLowerCase();
    var $items = $(".accordion-item");
    var $matchingItems = $items.filter(function () {
      return $(this).text().toLowerCase().indexOf(value) > -1;
    });
    // 알림창
    if ($matchingItems.length === 0) {
      Swal.fire({
        icon: "warning",
        title: "검색 결과가 없습니다.",
        text: "다른 검색어를 시도해보세요."
      });
    }
    $items.hide();
    $matchingItems.show();
  });
});
