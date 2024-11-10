function validateSignupForm() {
  const name = document.getElementById('name').value.trim();
  const email = document.getElementById('email').value.trim();
  const password = document.getElementById('password').value.trim();
  const confirmPassword = document.getElementById('confirm-password').value.trim();
  const errorMessage = document.getElementById('signup-error-message');

  if (!name || !email || !password || !confirmPassword) {
    errorMessage.textContent = '모든 필드를 입력해주세요.';
    errorMessage.style.display = 'block';
    return false;
  }
  if (password !== confirmPassword) {
    errorMessage.textContent = '비밀번호가 일치하지 않습니다.';
    errorMessage.style.display = 'block';
    return false;
  }

  errorMessage.style.display = 'none';
  return true;
}
