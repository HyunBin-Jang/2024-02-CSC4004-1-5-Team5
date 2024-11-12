document.addEventListener('DOMContentLoaded', function() {
  document.getElementById('signup-form').addEventListener('submit', function(event) {
      event.preventDefault();
      
      const name = document.getElementById('name').value.trim();
      const email = document.getElementById('email').value.trim();
      const password = document.getElementById('password').value;
      const confirmPassword = document.getElementById('confirm-password').value;

      if (password !== confirmPassword) {
          alert('비밀번호가 일치하지 않습니다.');
          return;
      }

      alert(`환영합니다, ${name}님! 회원가입이 완료되었습니다.`);
      
      // 회원가입 성공 후 로그인 페이지로 이동
      window.location.href = 'login.html'; // 원하는 로그인 페이지 URL로 변경하세요.
  });
});