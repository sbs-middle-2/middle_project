  // 비밀번호 팝업창
    const openModalButton = document.getElementById("openModalButton");
    const modal = document.getElementById("myModal");
    const closeModalButton = document.getElementById("closeModal");
    const changePasswordButton = document.getElementById("changePasswordButton");
    const newPasswordInput = document.getElementById("newPassword");
    const confirmPasswordInput = document.getElementById("confirmPassword");
    const otherButton = document.getElementById("otherButton");
    let isModalOpen = false; // 팝업 창 열림 상태 추가
    // 초기에 팝업 창 숨기기
    modal.style.display = "none";
    // 팝업 창을 열기 버튼 클릭 시 표시
    openModalButton.addEventListener("click", () => {
      if (!isModalOpen) {
        // 입력 필드 초기화
        newPasswordInput.value = "";
        confirmPasswordInput.value = "";
        modal.style.display = "block";
        isModalOpen = true;
        // 팝업 열 때 버튼 비활성화
        openModalButton.disabled = true;
      }
    });
    // 팝업 창을 닫기 버튼 클릭 시 숨김
    closeModalButton.addEventListener("click", () => {
      modal.style.display = "none";
      isModalOpen = false;
      // 팝업 닫을 때 버튼 활성화
      openModalButton.disabled = false;
    });
    // 비밀번호 변경 버튼 클릭 시
    changePasswordButton.addEventListener("click", () => {
      const newPassword = newPasswordInput.value;
      const confirmPassword = confirmPasswordInput.value;
      if (newPassword.trim() === "" || confirmPassword.trim() === "") {
        toastr.error("비밀번호를 입력하세요.");
      } else if (newPassword === confirmPassword) {
        toastr.success("비밀번호가 성공적으로 변경되었습니다.");
        modal.style.display = "none"; // 변경이 완료되면 팝업 창 닫기
        isModalOpen = false;
        // 팝업 닫을 때 버튼 활성화
        openModalButton.disabled = false;
      } else {
        toastr.error("비밀번호와 확인 비밀번호가 일치하지 않습니다. 다시 확인해주세요.");
      }
    });
    // ON 토쿨버튼
    const toggleBtn = document.getElementById("toggleBtn");
    const notificationBar = document.getElementById("notificationBar");
    let isOn = false; // 맨 처음 Off 상태로 시작
    toggleBtn.addEventListener("click", () => {
      if (isOn) {
        // Off로 변경
        toggleBtn.textContent = "Off";
        toggleBtn.classList.remove("on");
        showNotification("프로모션을 해제하셨습니다.");
      } else {
        // On으로 변경
        toggleBtn.textContent = "On";
        toggleBtn.classList.add("on");
        showNotification("프로모션 동의하셨습니다.");
      }
      isOn = !isOn;
    });

    function showNotification(message) {
      notificationBar.textContent = message;
      notificationBar.style.display = "block"; // 안내창 표시
      setTimeout(() => {
        notificationBar.style.display = "none"; // 몇 초 후에 안내창 숨김
      }, 3000); // 3초 후에 숨김 (3000 밀리초 = 3초)
    }