function validateForm() {
  const username = document.getElementById('username').value.trim();
  const password = document.getElementById('password').value.trim();
  const errorMessage = document.getElementById('error-message');

  if (username === '' || password === '') {
    errorMessage.textContent = '아이디와 비밀번호를 모두 입력해주세요.';
    errorMessage.style.display = 'block';
    return false;
  }

  errorMessage.style.display = 'none';

  // 로그인 성공 시 메인 페이지로 이동
  window.location.href = 'index.html';
  return true;
}
