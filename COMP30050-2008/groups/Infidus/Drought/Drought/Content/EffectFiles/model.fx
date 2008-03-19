//------- Constants --------
float4x4 xWorld;
float4x4 xView;
float4x4 xProjection;
float3 xLightDirection;
float xAmbient;
bool xEnableLighting;
bool xShowNormals;

//------- Texture Samplers --------

Texture xTexture;
sampler TextureSampler = sampler_state { texture = <xTexture> ; magfilter = LINEAR; minfilter = LINEAR; mipfilter=LINEAR; AddressU = mirror; AddressV = mirror;};

//------- Technique: Textured --------

struct VertexToPixel
{
    float4 Position          : POSITION;
    float4 Color             : COLOR0;
    float  LightingFactor    : TEXCOORD0;
    float2 TextureCoords     : TEXCOORD1;
};

struct PixelToFrame
{
    float4 Color : COLOR0;
};

VertexToPixel TexturedVertexShader( float4 inPos : POSITION, float3 inNormal: NORMAL, float2 inTexCoords: TEXCOORD0)
{	
	VertexToPixel Output = (VertexToPixel)0;
	float4x4 preViewProjection = mul (xView, xProjection);
	float4x4 preWorldViewProjection = mul (xWorld, preViewProjection);
    
	Output.Position = mul(inPos, preWorldViewProjection);	
	Output.TextureCoords = inTexCoords;
	
	float3 Normal = normalize(mul(normalize(inNormal), xWorld));	
	Output.LightingFactor = 1;
	if (xEnableLighting)
		Output.LightingFactor = dot(Normal, -xLightDirection);
    
	return Output;    
}

PixelToFrame TexturedPixelShader(VertexToPixel PSIn) 
{
	PixelToFrame Output = (PixelToFrame)0;		
	
	Output.Color = tex2D(TextureSampler, PSIn.TextureCoords)*clamp(PSIn.LightingFactor + xAmbient,0,1);

	return Output;
}


technique Textured
{
    pass Pass0
    {        
        VertexShader = compile vs_1_1 TexturedVertexShader();
        PixelShader = compile ps_1_1 TexturedPixelShader();
    }

} 