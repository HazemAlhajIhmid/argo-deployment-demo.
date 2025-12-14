import { writable } from 'svelte/store';

export type Language = 'ar' | 'en';

export const language = writable<Language>('ar');

export const translations = {
  ar: {
    // Header
    home: 'الرئيسية',
    riskCalculator: 'حاسبة المخاطر',
    about: 'حول المشروع',
    results: 'النتائج',
    contact: 'اتصل بنا',

    // Hero Section
    heroTitle: 'الكشف المبكر عن أمراض القلب',
    heroSubtitle: 'نظام ذكي يعتمد على خوارزميات تعلم الآلة للتنبؤ بمخاطر الإصابة بأمراض القلب',
    startAssessment: 'ابدأ التقييم الآن',
    learnMore: 'اعرف المزيد',

    // University Info
    university: 'الجامعة الافتراضية السورية',
    ministry: 'وزارة التعليم العالي - الجمهورية العربية السورية',
    researchTitle: 'تطوير خوارزميات التنقيب عن البيانات في تحسين عملية تشخيص أمراض القلب',
    student: 'الطالب: حازم خضر الحاج احميد',
    supervisors: 'المشرفون: د.م. جورج أنور كراز و د. ماجدة البكور',

    // Risk Calculator
    calculatorTitle: 'حاسبة مخاطر أمراض القلب',
    calculatorSubtitle: 'أدخل البيانات الطبية للحصول على تقييم دقيق لمخاطر الإصابة',

    // Form Labels
    age: 'العمر',
    sex: 'الجنس',
    male: 'ذكر',
    female: 'أنثى',
    cp: 'نوع ألم الصدر',
    cpType0: 'غير معروف',
    cpType1: 'ذبحة صدرية نموذجية',
    cpType2: 'ذبحة صدرية غير نموذجية',
    cpType3: 'ألم غير ذبحي',
    cpType4: 'بدون أعراض',
    trestbps: 'ضغط الدم أثناء الراحة (mmHg)',
    chol: 'الكوليسترول في الدم (mg/dl)',
    fbs: 'سكر الدم أثناء الصيام > 120 mg/dl',
    yes: 'نعم',
    no: 'لا',
    restecg: 'نتائج تخطيط القلب أثناء الراحة',
    restecgType0: 'طبيعي',
    restecgType1: 'شذوذ ST-T',
    restecgType2: 'تضخم البطين الأيسر',
    thalach: 'أقصى معدل ضربات القلب',
    exang: 'ذبحة صدرية ناتجة عن التمارين',
    oldpeak: 'انخفاض ST بسبب التمارين',
    slope: 'ميل قطعة ST',
    slopeType0: 'صاعد',
    slopeType1: 'مستوي',
    slopeType2: 'هابط',
    ca: 'عدد الأوعية الرئيسية (0-3)',
    thal: 'اختبار الثاليوم',
    thalType0: 'غير معروف',
    thalType1: 'طبيعي',
    thalType2: 'عيب ثابت',
    thalType3: 'عيب قابل للإصلاح',

    // Buttons
    calculate: 'احسب المخاطر',
    reset: 'إعادة تعيين',
    calculating: 'جاري الحساب...',

    // Results
    riskLevel: 'مستوى المخاطر',
    lowRisk: 'منخفض',
    moderateRisk: 'متوسط',
    highRisk: 'مرتفع',
    prediction: 'التنبؤ',
    hasDisease: 'احتمالية الإصابة بمرض القلب',
    noDisease: 'احتمالية عدم الإصابة بمرض القلب',
    confidence: 'نسبة الثقة',

    // Models
    modelsTitle: 'مقارنة النماذج',
    knnModel: 'نموذج KNN',
    naiveBayesModel: 'نموذج Naive Bayes',
    decisionTreeModel: 'نموذج Decision Tree',
    accuracy: 'الدقة',
    precision: 'الدقة (Precision)',
    recall: 'الاستدعاء (Recall)',
    f1Score: 'F1-Score',

    // Research Info
    researchMethodology: 'منهجية البحث',
    dataCollection: 'جمع البيانات',
    dataCollectionDesc: 'تم استخدام بيانات UCI لأمراض القلب التي تحتوي على 303 سجلات و14 متغيراً سريرياً',
    preprocessing: 'معالجة البيانات',
    preprocessingDesc: 'تطبيق StandardScaler وتقسيم البيانات إلى 80% تدريب و20% اختبار',
    modelTraining: 'تدريب النماذج',
    modelTrainingDesc: 'تدريب ثلاثة نماذج: KNN، Naive Bayes، Decision Tree',
    evaluation: 'التقييم',
    evaluationDesc: 'استخدام مقاييس Accuracy، Precision، Recall، F1-score',

    // Footer
    rightsReserved: 'جميع الحقوق محفوظة',
    contactInfo: 'معلومات الاتصال',
  },
  en: {
    // Header
    home: 'Home',
    riskCalculator: 'Risk Calculator',
    about: 'About',
    results: 'Results',
    contact: 'Contact',

    // Hero Section
    heroTitle: 'Early Detection of Heart Disease',
    heroSubtitle: 'AI-powered system using machine learning algorithms to predict heart disease risk',
    startAssessment: 'Start Assessment',
    learnMore: 'Learn More',

    // University Info
    university: 'Syrian Virtual University',
    ministry: 'Ministry of Higher Education - Syrian Arab Republic',
    researchTitle: 'Develop Data Mining Algorithms to Improve the Diagnosis of Heart Disease',
    student: 'Student: Hazem Khader Al-Haj Ahmid',
    supervisors: 'Supervisors: Dr. George Anwar Karraz & Dr. Majeda Al-Bakour',

    // Risk Calculator
    calculatorTitle: 'Heart Disease Risk Calculator',
    calculatorSubtitle: 'Enter medical data to get an accurate risk assessment',

    // Form Labels
    age: 'Age',
    sex: 'Sex',
    male: 'Male',
    female: 'Female',
    cp: 'Chest Pain Type',
    cpType0: 'Unknown',
    cpType1: 'Typical Angina',
    cpType2: 'Atypical Angina',
    cpType3: 'Non-anginal Pain',
    cpType4: 'Asymptomatic',
    trestbps: 'Resting Blood Pressure (mmHg)',
    chol: 'Serum Cholesterol (mg/dl)',
    fbs: 'Fasting Blood Sugar > 120 mg/dl',
    yes: 'Yes',
    no: 'No',
    restecg: 'Resting ECG Results',
    restecgType0: 'Normal',
    restecgType1: 'ST-T Abnormality',
    restecgType2: 'Left Ventricular Hypertrophy',
    thalach: 'Maximum Heart Rate',
    exang: 'Exercise Induced Angina',
    oldpeak: 'ST Depression by Exercise',
    slope: 'Slope of Peak Exercise ST',
    slopeType0: 'Upsloping',
    slopeType1: 'Flat',
    slopeType2: 'Downsloping',
    ca: 'Number of Major Vessels (0-3)',
    thal: 'Thalassemia Test',
    thalType0: 'Unknown',
    thalType1: 'Normal',
    thalType2: 'Fixed Defect',
    thalType3: 'Reversible Defect',

    // Buttons
    calculate: 'Calculate Risk',
    reset: 'Reset',
    calculating: 'Calculating...',

    // Results
    riskLevel: 'Risk Level',
    lowRisk: 'Low',
    moderateRisk: 'Moderate',
    highRisk: 'High',
    prediction: 'Prediction',
    hasDisease: 'Heart Disease Likely',
    noDisease: 'Heart Disease Unlikely',
    confidence: 'Confidence',

    // Models
    modelsTitle: 'Models Comparison',
    knnModel: 'KNN Model',
    naiveBayesModel: 'Naive Bayes Model',
    decisionTreeModel: 'Decision Tree Model',
    accuracy: 'Accuracy',
    precision: 'Precision',
    recall: 'Recall',
    f1Score: 'F1-Score',

    // Research Info
    researchMethodology: 'Research Methodology',
    dataCollection: 'Data Collection',
    dataCollectionDesc: 'Used UCI Heart Disease dataset with 303 records and 14 clinical variables',
    preprocessing: 'Data Preprocessing',
    preprocessingDesc: 'Applied StandardScaler and split data into 80% training and 20% testing',
    modelTraining: 'Model Training',
    modelTrainingDesc: 'Trained three models: KNN, Naive Bayes, Decision Tree',
    evaluation: 'Evaluation',
    evaluationDesc: 'Used Accuracy, Precision, Recall, F1-score metrics',

    // Footer
    rightsReserved: 'All Rights Reserved',
    contactInfo: 'Contact Information',
  }
};

export function t(key: string, lang: Language = 'ar'): string {
  return translations[lang][key as keyof typeof translations['ar']] || key;
}
