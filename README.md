CORE BANKING CONSOLE SYSTEM
===========================

Features:
- Customer registration/login
- Savings and Current accounts
- Balance enquiry
- Deposit
- Withdrawal
- Money transfer with deterministic account locking
- Mini statement
- Transaction history
- OTP-based PIN change through registered email
- Email mini statement

Fixed Deposit and Loan modules are intentionally removed.

SETUP
-----
1. Install Java 25 or another modern JDK.
2. Put these two JARs in lib/:
   javax.mail-1.6.2.jar
   activation-1.1.1.jar
3. Open config/email.properties and enter:
   BANKING_EMAIL=your Gmail address
   BANKING_EMAIL_APP_PASSWORD=your Google App Password
4. Run run.bat.

AVAST/TLS NOTE
--------------
This build uses Gmail SMTP SSL on port 465 and JavaMail's targeted
mail.smtp.ssl.trust=smtp.gmail.com setting. It is included specifically
for machines where antivirus SMTP/TLS inspection presents a locally
generated smtp.gmail.com certificate (such as Avast Web/Mail Shield).
It avoids disabling antivirus protection.

SECURITY NOTE
-------------
Do not upload config/email.properties to GitHub. A Google App Password
must never be committed to source control.
