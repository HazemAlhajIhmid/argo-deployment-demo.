using HeartDiseaseAPI.Services;

var builder = WebApplication.CreateBuilder(args);

// Add services to the container
builder.Services.AddControllers();
builder.Services.AddEndpointsApiExplorer();
builder.Services.AddSwaggerGen(options =>
{
    options.SwaggerDoc("v1", new Microsoft.OpenApi.Models.OpenApiInfo
    {
        Title = "Heart Disease Prediction API",
        Version = "v1",
        Description = @"
Advanced AI-powered Heart Disease Early Detection System

**Syrian Virtual University Master's Thesis Project**

**Title:** Develop Data Mining Algorithms to Improve the Diagnosis of Heart Disease

**Student:** Hazem Kheder Al-Haj Ahmid (Hazem_82763@svuonline.org)

**Supervisors:**
- Dr. George Anwar Karraz (Main Supervisor)
- Dr. Majeda Bakour (Co-Supervisor)

**Machine Learning Models:**
- K-Nearest Neighbors (KNN) - Accuracy: 82%
- Naive Bayes - Accuracy: 82%
- Decision Tree - Accuracy: 70%

**Dataset:** UCI Heart Disease Dataset (303 records, 14 medical parameters)

**Ministry of Higher Education - Syrian Arab Republic**
",
        Contact = new Microsoft.OpenApi.Models.OpenApiContact
        {
            Name = "Hazem Kheder Al-Haj Ahmid",
            Email = "Hazem_82763@svuonline.org"
        }
    });
});

// Configure CORS for Svelte frontend
builder.Services.AddCors(options =>
{
    options.AddPolicy("AllowSvelteFrontend", policy =>
    {
        policy.WithOrigins("http://localhost:5173", "http://localhost:3000")
              .AllowAnyHeader()
              .AllowAnyMethod();
    });
});

// Register HeartDiseasePredictionService as Singleton
var dataPath = Path.Combine(Directory.GetCurrentDirectory(), "Data", "heart.csv");
builder.Services.AddSingleton(new HeartDiseasePredictionService(dataPath));

var app = builder.Build();

// Configure the HTTP request pipeline
if (app.Environment.IsDevelopment())
{
    app.UseSwagger();
    app.UseSwaggerUI();
}

app.UseHttpsRedirection();
app.UseCors("AllowSvelteFrontend");
app.UseAuthorization();
app.MapControllers();

// Train ML models on startup
var predictionService = app.Services.GetRequiredService<HeartDiseasePredictionService>();
Console.WriteLine("Training ML models...");
await predictionService.TrainModelsAsync();
Console.WriteLine("ML models trained successfully!");

app.Run();
